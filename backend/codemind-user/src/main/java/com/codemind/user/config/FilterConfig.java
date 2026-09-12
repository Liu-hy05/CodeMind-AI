package com.codemind.user.config;


import com.codemind.user.filter.JwtAuthenticationFilter;
import jakarta.annotation.Resource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {


    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilter(){

        FilterRegistrationBean<JwtAuthenticationFilter> registration =
                new FilterRegistrationBean<>();


        registration.setFilter(jwtAuthenticationFilter);


        registration.addUrlPatterns("/*");


        registration.setOrder(1);


        return registration;
    }

}