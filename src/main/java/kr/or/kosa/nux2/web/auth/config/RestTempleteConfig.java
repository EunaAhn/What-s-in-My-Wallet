package kr.or.kosa.nux2.web.auth.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTempleteConfig {


    @Bean
    public RestTemplate restTemplate (RestTemplateBuilder builder) {
        return builder.
                rootUri("127.0.0.1:5000")
                .build();
    }
}
