package kr.or.kosa.nux2.domain.member.service;

import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.dto.Role;
import kr.or.kosa.nux2.domain.member.repository.EmailAuthenticationRepository;
import kr.or.kosa.nux2.domain.member.repository.MemberExpenditureCategoryRepository;
import kr.or.kosa.nux2.domain.member.repository.MemberRepository;
import kr.or.kosa.nux2.web.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private static final String URL = "https://accounts.google.com/o/oauth2/revoke?token=";
    private RestTemplate restTemplate = new RestTemplate();
    private final MemberRepository memberRepository;
    private final EmailAuthenticationRepository emailAuthenticationRepository;
    private final MemberExpenditureCategoryRepository memberExpenditureCategoryRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender javaMailSender;


    @Override
    public String test() {
        return "context 분리 test";
    }

    @Override
    public String logout(CustomUserDetails customUserDetails) {
        log.info("method = {}","logout");
        if(customUserDetails.getUserDto().getProvider()!=null) {
            if (customUserDetails.getUserDto().getProvider().equals("google")) {
                String socialAccessToken = customUserDetails.getUserDto().getSocialToken();
                restTemplate.getForObject(URL + socialAccessToken, String.class);
            }
        }
        return "logout";

    }

    @Transactional
    @Override
    public String signIn(MemberDto.SignInRequest request) {
        request.setMemberPassword(bCryptPasswordEncoder.encode(request.getMemberPassword()));
        request.setRole(Role.USER.getRoles());
        memberRepository.insertMember(request);
        memberExpenditureCategoryRepository.insertMemberConsCategory(request.getMemberConsCategoryDtoList());
        return "main";

    }

    @Override
    public boolean checkMemberId(MemberDto.MemberIdRequest request) {
        if(memberRepository.isExistMemberId(request)){
            return false;
        }else{
            sendEmail(request);
            return true;
        }
    }
    @Transactional
    @Override
    public void sendEmail(MemberDto.MemberIdRequest request) {
        String authenticationNumber = generateAuthenticationNumber();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("what's in my wallet 인증메일");
        mailMessage.setText("인증번호: "+ authenticationNumber);
        mailMessage.setTo(request.getMemberId());
        javaMailSender.send(mailMessage);
        insertAuthenticationInfo(new MemberDto.AuthenticationRequest(request.getMemberId(),authenticationNumber));
    }

    @Override
    public String generateAuthenticationNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        return String.valueOf(randomNumber);
    }

    @Override
    public int insertAuthenticationInfo(MemberDto.AuthenticationRequest request) {
       return emailAuthenticationRepository.insertAuthenticationInfo(request);
    }
    @Transactional
    @Override
    public boolean validateAuthenticationNumber(MemberDto.AuthenticationRequest request) {
        MemberDto.AuthenticationResponse response = emailAuthenticationRepository.findAuthenticationNumberByMemberIdAndTimeDiffLessThanFiveMinute(request);
        MemberDto.MemberIdRequest memberIdRequest = new MemberDto.MemberIdRequest(request.getMemberId());
        if(response!=null){
            if(response.getAuthenticationNumber().equals(request.getAuthenticationNumber())){
                emailAuthenticationRepository.deleteAuthenticationInfoByMemberId(memberIdRequest);
                return true;
            }
        }
        emailAuthenticationRepository.deleteAuthenticationInfoByMemberId(memberIdRequest);
        return false;
    }

}
