package kr.or.kosa.nux2.web.auth.filter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.nux2.web.auth.JwtUtils;
import kr.or.kosa.nux2.web.auth.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("method = {}", "onAuthenicationSuccess");
        String accessToken = jwtUtils.createAccessToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.addHeader(HttpHeaders.AUTHORIZATION, accessToken);

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        if (customUserDetails.getUserDto().getStatus() == 0) {
            response.sendRedirect("/api/member/profile?status=0");
        } else {
            response.sendRedirect("/main");
        }
    }
}
