package kr.or.kosa.nux2.domain.member.service;

import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.repository.MemberExpenditureCategoryRepository;
import kr.or.kosa.nux2.domain.member.repository.MemberRepository;
import kr.or.kosa.nux2.web.auth.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
    private static final String URL = "https://accounts.google.com/o/oauth2/revoke?token=";
    private RestTemplate restTemplate;
    private MemberRepository memberRepository;
    private MemberExpenditureCategoryRepository memberExpenditureCategoryRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MemberExpenditureCategoryRepository memberExpenditureCategoryRepository) {
        this.restTemplate = new RestTemplate();
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.memberExpenditureCategoryRepository = memberExpenditureCategoryRepository;
    }

    @Override
    public String test() {
        return "context 분리 test";
    }

    @Override
    public String logout(CustomUserDetails customUserDetails) {
        log.info("method = {}","login");
        if(customUserDetails.getUserDto().getProvider().equals("google")){
            String socialAccessToken = customUserDetails.getUserDto().getSocialToken();
            restTemplate.getForObject(URL+socialAccessToken,String.class);
        }
        return "logout";

    }

    @Transactional
    @Override
    public String signIn(MemberDto.SignInRequest request) {
        memberRepository.insertMember(request);
        memberExpenditureCategoryRepository.insertMemberConsCategory(request.getMemberConsCategoryDtoList());
        return "main";

    }

}
