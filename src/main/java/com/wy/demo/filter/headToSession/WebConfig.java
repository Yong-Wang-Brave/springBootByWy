package com.wy.demo.filter.headToSession;


import cn.hutool.extra.spring.SpringUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {


    public FilterRegistrationBean SecurityFilterRegister(){
        filterAddToSession securityFilter =new filterAddToSession();
        SpringUtil.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(securityFilter);

                FilterRegistrationBean filterRegistration =new FilterRegistrationBean();
        filterRegistration.setFilter(securityFilter);
        filterRegistration.addUrlPatterns("/add");
        return  filterRegistration;


    }
}

