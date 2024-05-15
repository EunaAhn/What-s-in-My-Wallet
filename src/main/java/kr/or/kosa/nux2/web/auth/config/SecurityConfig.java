package kr.or.kosa.nux2.web.auth.config;

import kr.or.kosa.nux2.web.auth.filter.JwtAuthenticationEntryPoint;
import kr.or.kosa.nux2.web.auth.filter.JwtFilter;
import kr.or.kosa.nux2.web.auth.filter.JwtLoginFilter;
import kr.or.kosa.nux2.web.auth.filter.OAuthSuccessHandler;
import kr.or.kosa.nux2.web.auth.principal.CustomOauth2UserService;
import kr.or.kosa.nux2.web.auth.*;
import kr.or.kosa.nux2.web.auth.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtProvider jwtProvider;
    private final JwtUtils jwtUtils;
    private final CustomOauth2UserService customOauth2UserService;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Collections.singletonList(jwtProvider));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf((auth) -> auth.disable());
        http
                .formLogin((auth) -> auth.disable());
//                .formLogin((auth) -> auth.permitAll());
        http
                .httpBasic((auth) -> auth.disable());
        http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/test/*", "/api/expenditure/*", "/api/cardproduct/*", "/api/registrationcard/*","/api/cardreco/*","/api/mydata/*", "/mycard", "/api/cardproduct/*").permitAll());
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger", "/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**", "/v3/api-docs/**")
                        .permitAll()
                        .requestMatchers("/email/**", "/user", "/login/**", "/auth/**", "/oauth2/**", "/signUp")
                        .permitAll()
                        .requestMatchers("/analyze/**", "/carddetail/**", "/cardlist/**", "/cardregistration/**", "/history/**", "/onboarding/**", "/profile/**", "/signup/**", "/suggestion/**")
                        .permitAll()
//                        .requestMatchers("/", "/error", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js")
                        .requestMatchers("/", "/error", "/favicon.ico", "/png/**", "/gif/**", "/svg/**", "/jpg/**", "/html/**", "/css/**", "/js/**", "/img/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated());
        //.logout((logout)->logout.logoutUrl("/logout").logoutSuccessUrl("/login"));

        http
                .formLogin((auth) -> auth.loginPage("/login").defaultSuccessUrl("/main").permitAll());
        http
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/profile", true)
                        .successHandler(new OAuthSuccessHandler(jwtUtils))
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOauth2UserService))
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
