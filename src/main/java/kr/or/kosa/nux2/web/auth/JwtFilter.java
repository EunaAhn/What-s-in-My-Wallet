package kr.or.kosa.nux2.web.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    // 삭제 예정 화내지마요 혜미씨!!
    private AntPathMatcher pathMatcher = new AntPathMatcher();
    private List<String> excludeUrlPatterns = new ArrayList<>(Arrays.asList(
            "/", "/error", "/favicon.ico", "/*.png", "/*.gif", "/*.svg", "/*.jpg", "/*.html", "/*.css", "/*.js"
    ));

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return excludeUrlPatterns.stream().anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("method = {}","doFilterInternal");
        String token = jwtUtils.findToken(request);

        if (token != null && jwtUtils.validateToken(token)) { //토큰이 유효할때
            String userName = jwtUtils.extractUsername(token);

            if (userName != null && !userName.equalsIgnoreCase("")) { //유저 아이디 있을때
                log.info("set authentication");
                SecurityContextHolder.getContext().setAuthentication(jwtUtils.getAuthentication(token));
            }else {
                log.info("not exist userName");
            }

        } else {
            log.info("invalid token");
        }
        filterChain.doFilter(request, response);
    }
}