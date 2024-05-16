package kr.or.kosa.nux2.domain.member.service;

import kr.or.kosa.nux2.domain.member.dto.MemberConsCategoryDto;
import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.dto.Role;
import kr.or.kosa.nux2.domain.member.repository.AuthenticationRepository;
import kr.or.kosa.nux2.domain.member.repository.MemberExpenditureCategoryRepository;
import kr.or.kosa.nux2.domain.member.repository.MemberRepository;
import kr.or.kosa.nux2.web.auth.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private static final String URL = "https://accounts.google.com/o/oauth2/revoke?token=";
    private RestTemplate restTemplate = new RestTemplate();
    private final MemberRepository memberRepository;
    private final MemberExpenditureCategoryRepository memberExpenditureCategoryRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender javaMailSender;
    private final AuthenticationRepository authenticationRepository;

    /**
     * 로그아웃 함수
     *
     * @param customUserDetails: 로그인한 유저 정보
     * @return : 로그인 페이지
     */
    @Override
    public String logout(CustomUserDetails customUserDetails) {
        log.info("method = {}", "logout");
        if (customUserDetails.getUserDto().getProvider() != null) {
            if (customUserDetails.getUserDto().getProvider().equals("google")) {
                String socialAccessToken = customUserDetails.getUserDto().getSocialToken();
                restTemplate.getForObject(URL + socialAccessToken, String.class);
            }
        }
        SecurityContextHolder.clearContext();
        return "login";
    }

    /**
     * 회원가입 함수
     *
     * @param request: 회원가입 정보
     * @return: 성공, 실패 시 해당 페이지 이동
     */
    @Transactional
    @Override
    public String signUp(MemberDto.SignUpRequest request) {
        MemberDto.UserDto findUserDto = memberRepository.findById(request.getMemberId());
        if (findUserDto == null) {
            request.setMemberPassword(bCryptPasswordEncoder.encode(request.getMemberPassword()));
            request.setRole(Role.USER.getRoles());
            memberRepository.insertMember(request);

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("memberId", request.getMemberId());
            paramMap.put("list", request.getMemberConsCategoryDtoList());

            memberExpenditureCategoryRepository.insertMemberConsCategory(paramMap);
            return "main";
        }
        return "signup";
    }

    /**
     * 존재하지 않는 아이디(이메일)인지 확인하는 함수
     *
     * @param request: 아이디(이메일)
     * @return: 아이디 존재하지 않으면 true, 존재하면 false
     */
    @Override
    public boolean checkMemberId(MemberDto.MemberIdRequest request) {
        if (memberRepository.isExistMemberId(request)) {
            return false;
        } else {
            sendEmail(request);
            return true;
        }
    }

    /**
     * 이메일 발송 함수
     *
     * @param request: 아이디(이메일)
     */
    @Transactional
    @Override
    public void sendEmail(MemberDto.MemberIdRequest request) {
        String authenticationNumber = generateAuthenticationNumber();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("what's in my wallet 인증메일");
        mailMessage.setText("인증번호: " + authenticationNumber);
        mailMessage.setTo(request.getMemberId());
        javaMailSender.send(mailMessage);

        authenticationRepository.save(request.getMemberId(), authenticationNumber);

    }

    /**
     * 인증번호 생성 함수
     *
     * @return 6자리 랜덤숫자
     */
    @Override
    public String generateAuthenticationNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        return String.valueOf(randomNumber);
    }

    /**
     * 인증번호 검증 함수
     *
     * @param request: 입력한 번호
     * @return 인증번호와 같으면 true, 다르면 false
     */
    @Transactional
    @Override
    public boolean validateAuthenticationNumber(MemberDto.AuthenticationRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        String authenticationNumber = authenticationRepository.findById(memberId);

        if (authenticationNumber != null && authenticationNumber.equals(request.getAuthenticationNumber())) {
            authenticationRepository.delete(memberId);
            return true;
        }
        return false;
    }

    /**
     * 마이페이지 프로필 출력 함수
     *
     * @return 프로필 정보
     */
    @Transactional
    @Override
    public MemberDto.ProfileResponse showMemberProfile() {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        MemberDto.MemberIdRequest request = new MemberDto.MemberIdRequest(memberId);

        List<MemberConsCategoryDto.MemberConsCategoryResponse> memberConsCategoryDtoList = memberExpenditureCategoryRepository.selectMemberConsCategoryNames(request);

        MemberDto.ProfileResponse response = memberRepository.selectMemberDetail(request);
        response.setMemberConsCategoryDtoList(memberConsCategoryDtoList);
        return response;
    }

    /**
     * 마이페이지 정보 수정
     *
     * @param request: 회원 관심카테고리, 월목표지출액 수정 정보
     * @return: 변경된 회원 정보
     */
    @Transactional
    @Override
    public MemberDto.ProfileResponse updateMemberInfo(MemberDto.UpdateMemberInfoRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("memberId", memberId);
        paramMap.put("targetExpenditure", request.getTargetExpenditure());
        paramMap.put("memberConsCategoryIdDtoList", request.getMemberConsCategoryIdDtoList());

        MemberDto.ProfileResponse response = updateTargetExpenditure(paramMap);
        response.setMemberId(memberId);
        response.setMemberConsCategoryDtoList(updateMemberConsCategoryList(paramMap));

        return response;
    }

    /**
     * 비밀번호 변경 함수
     *
     * @param request: 변경할 비밀번호, 확인 비밀번호
     * @return: 변경되면 true, 변경되지 않았으면 false
     */
    @Override
    public boolean updatePassword(MemberDto.UpdatePasswordRequest request) {
        if (request.getChangePassword().equals(request.getCheckPassword())) {
            String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("memberId", memberId);
            paramMap.put("memberPassword", bCryptPasswordEncoder.encode(request.getChangePassword()));

            int count = memberRepository.updatePassword(paramMap);
            if (count == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public MemberDto.ProfileResponse updateTargetExpenditure(Map<String, Object> paramMap) {
        return memberRepository.findMemberNameAndTargetExpenditureByMemberId(paramMap);
    }

    /**
     * 관심 카테고리 리스트 수정
     *
     * @param paramMap: 수정된 관심 카테고리 리스트
     * @return: 수정된 후 관심 카테고리 리스트
     */
    @Transactional
    @Override
    public List<MemberConsCategoryDto.MemberConsCategoryResponse> updateMemberConsCategoryList(Map<String, Object> paramMap) {
        List<MemberConsCategoryDto.MemberConsCategoryIdDto> savedList = memberExpenditureCategoryRepository.selectMemberConsCategoryIds(paramMap);
        List<MemberConsCategoryDto.MemberConsCategoryIdDto> changeList = (List<MemberConsCategoryDto.MemberConsCategoryIdDto>) paramMap.get("memberConsCategoryIdDtoList");

        for (MemberConsCategoryDto.MemberConsCategoryIdDto item : changeList) {
            if (savedList.contains(item)) {
                savedList.remove(item);
                changeList.remove(item);
            }
        }

        paramMap.put("list", savedList);
        memberExpenditureCategoryRepository.deleteMemberConsCategory(paramMap);

        paramMap.put("list", changeList);
        memberExpenditureCategoryRepository.insertMemberConsCategory(paramMap);

        return memberExpenditureCategoryRepository.selectMemberConsCategoryNames(new MemberDto.MemberIdRequest(paramMap.get("memberId").toString()));
    }
}
