package kr.or.kosa.nux2.web.auth.provider;

import kr.or.kosa.nux2.web.auth.principal.CustomUserDetailsService;
import kr.or.kosa.nux2.web.auth.authentication.JwtAuthenticationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider implements AuthenticationProvider {
    private final CustomUserDetailsService customUserDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
    필터에서 전달된 authentication에 토큰 저장되어 있음
    토큰에서 유저의 아이디와 권한 가져오고 jwtAuthenticationToken에 토큰, 유저, 권한 담아서 보내줌
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("method = {}", "authenticate");
        String memberId = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(memberId);
        if (userDetails != null && memberId.equals(userDetails.getUsername())) {
            if (bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                return new JwtAuthenticationToken(userDetails, userDetails.getPassword(), authorities);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
