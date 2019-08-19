package cn.com.agree.aweb.configuration;

import cn.com.agree.aweb.feign.interceptor.AuthTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public AuthTokenInterceptor basicAuthRequestInterceptor() {
        return new AuthTokenInterceptor();
    }

}
