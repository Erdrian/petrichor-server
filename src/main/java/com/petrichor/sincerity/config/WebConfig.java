package com.petrichor.sincerity.config;

import com.petrichor.sincerity.controller.interceptor.AuthorizeInterceptor;
import com.petrichor.sincerity.controller.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Autowired
    LoginInterceptor loginInterceptor;
    @Autowired
    AuthorizeInterceptor authorizeInterceptor;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins(
                                "http://localhost:8088",
                                "http://localhost:8099",
                                "http://192.168.86.122:8088",
                                "http://127.0.0.1:8088",
                                "http://127.0.0.1:4173"
                        )
                        .allowedMethods("GET", "POST")
                        .allowCredentials(true)
                        .maxAge(3600);
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(loginInterceptor).addPathPatterns("/api/**");
                registry.addInterceptor(authorizeInterceptor).addPathPatterns("/api/**");
            }
        };
    }
}
