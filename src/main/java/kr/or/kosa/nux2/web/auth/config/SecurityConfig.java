package kr.or.kosa.nux2.web.auth.config;

import kr.or.kosa.nux2.web.auth.PrincipleOauth2UserService;
import kr.or.kosa.nux2.web.auth.*;
import kr.or.kosa.nux2.web.auth.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtProvider jwtProvider;
    private final JwtUtils jwtUtils;
    private final PrincipleOauth2UserService principleOauth2UserService;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Collections.singletonList(jwtProvider));
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // jwt 토큰을 사용하지 않는 URL
        String[] permitUrl = {"/oauth2/**", "/", "/login/**", "/signUp/**", "/product/**", "/refresh", "/swagger-ui/index.html","/swagger**.html", "/api-docs/**"};

        http
                .csrf((auth) -> auth.disable());
        http
              .formLogin((auth) -> auth.disable());
//                .formLogin((auth) -> auth.permitAll());
        http
                .httpBasic((auth) -> auth.disable());

        http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/cardproduct/*", "/api/expenditure/*").permitAll());
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger", "/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**", "/v3/api-docs/**")
                        .permitAll()
                        .requestMatchers("/user","/login/**","/auth/**","/oauth2/**")
                        .permitAll()
                        .requestMatchers("/analyze/**","/carddetail/**", "/cardlist/**","/cardregistration/**","/history/**","/onboarding/**", "/profile/**","/signup/**","/suggestion/**")
                        .permitAll()
//                        .requestMatchers("/", "/error", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js")
                        .requestMatchers("/", "/error", "/favicon.ico","/png/**", "/gif/**", "/svg/**", "/jpg/**", "/html/**", "/css/**", "/js/**","/img/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated());
                        //.logout((logout)->logout.logoutUrl("/logout").logoutSuccessUrl("/login"));

        http
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(principleOauth2UserService))
                        .successHandler(new OAuthSuccessHandler(jwtUtils))



                );
        http
                .exceptionHandling((exceptions) -> exceptions.authenticationEntryPoint(jwtAuthenticationEntryPoint));
        http
                .addFilterBefore(new JwtFilter(jwtUtils), JwtLoginFilter.class);
        http
                .addFilterAt(new JwtLoginFilter(authenticationManager(), jwtUtils), UsernamePasswordAuthenticationFilter.class);
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        return http.build();
    }


}
