package com.wy.demo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

//@Configuration
public class FilterConfig {
    /**
     * 配置过滤器，这里过滤器主要是对返回值做后继处理
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration()
    {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ResponseFilter());// 配置一个返回值加密过滤器
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("responseFilter");
        return registration;
    }
}
