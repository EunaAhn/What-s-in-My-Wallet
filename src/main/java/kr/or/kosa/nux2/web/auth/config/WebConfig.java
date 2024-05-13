package kr.or.kosa.nux2.web.auth.config;

import kr.or.kosa.nux2.web.controller.Interceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final Interceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                //TODO excludeURL 좀더 고민해보기
                .excludePathPatterns("/email/**","/user","/login/**","/auth/**","/oauth2/**","/signIn","/profile");
    }
}
