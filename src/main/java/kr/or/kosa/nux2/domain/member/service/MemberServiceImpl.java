package kr.or.kosa.nux2.domain.member.service;

import kr.or.kosa.nux2.web.auth.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
    private static final String URL = "https://accounts.google.com/o/oauth2/revoke?token=";
    private RestTemplate restTemplate;

    public MemberServiceImpl() {
        this.restTemplate = new RestTemplate();
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

}
