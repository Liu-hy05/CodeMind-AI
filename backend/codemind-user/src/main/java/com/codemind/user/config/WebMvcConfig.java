package com.codemind.user.config;


import com.codemind.user.interceptor.RoleInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Resource
    private RoleInterceptor roleInterceptor;


    @Override
    public void addInterceptors(
            InterceptorRegistry registry
    ) {

        registry.addInterceptor(roleInterceptor)
                .addPathPatterns("/**");

    }

}