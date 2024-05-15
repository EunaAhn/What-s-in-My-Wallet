package kr.or.kosa.nux2.web.auth.principal;

import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException { // 구글로받은 request 후처리 함수
        log.info("method = {}", "loadUser");
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String memberId = oAuth2User.getAttribute("email");
        String socialToken = userRequest.getAccessToken().getTokenValue();
        String memberName = oAuth2User.getAttribute("name");

        Map<String, Object> paramMap = new HashMap<>();

        MemberDto.UserDto userDto = memberRepository.findById(memberId);
        if (userDto == null) {
            userDto = MemberDto.UserDto.of(registrationId, memberId, memberName, socialToken);
        }

        paramMap.put("memberId", userDto.getMemberId());
        paramMap.put("memberName", userDto.getMemberName());
        paramMap.put("memberPassword", userDto.getMemberPassword());
        paramMap.put("role", userDto.getRole());
        paramMap.put("provider", userDto.getProvider());
        paramMap.put("socialToken", userDto.getSocialToken());
        paramMap.put("status", userDto.getStatus());
        memberRepository.saveOrUpdateMember(paramMap);

        return new CustomUserDetails(userDto, oAuth2User.getAttributes());

    }
}
