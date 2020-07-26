package com.tel.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
/**
    @Bean
    public AuthorizerInterceptor AuthorizerInterceptor() {
        return new AuthorizerInterceptor();
    }

    public @Override void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(AuthorizerInterceptor());
    }
    */
	@Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
    }	
}