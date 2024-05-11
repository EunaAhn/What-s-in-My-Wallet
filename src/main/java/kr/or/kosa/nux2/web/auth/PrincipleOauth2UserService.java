package kr.or.kosa.nux2.web.auth;

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

@Slf4j
@RequiredArgsConstructor
@Service
public class PrincipleOauth2UserService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException { // 구글로받은 request 후처리 함수
        log.info("method = {}","loadUser");
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String memberId = registrationId+"-"+providerId;
        String email = oAuth2User.getAttribute("email");
        String password = bCryptPasswordEncoder.encode("구글");
        String socialToken = userRequest.getAccessToken().getTokenValue();
        String memberName = oAuth2User.getAttribute("name");


        MemberDto.UserDto userDto = memberRepository.findById(memberId);
        if(userDto==null){
            userDto = MemberDto.UserDto.of(registrationId,memberId,memberName,email,password,providerId,socialToken);
            memberRepository.save(userDto);
        }else{
            userDto.setSocialToken(socialToken);
            memberRepository.updateSocialToken(new MemberDto.UpdateSocialTokenRequest(memberId,socialToken));
        }

        return new CustomUserDetails(userDto, oAuth2User.getAttributes());

    }
}
