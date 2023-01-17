package com.wy.demo.自定义注解.demo2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class MyWebMVCConfig implements WebMvcConfigurer {
    // 重写WebMvcCOnfigurer的addInterceptors方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册AuthInterceptor拦截器
        registry.addInterceptor(new AuthInterceptor());
    }
}
