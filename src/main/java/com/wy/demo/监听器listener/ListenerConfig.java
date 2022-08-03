package com.wy.demo.监听器listener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean slrBean = new ServletListenerRegistrationBean();
        slrBean.setListener(new WebListenerDemo());
        return slrBean;
    }
 
    @Bean
    public ServletListenerRegistrationBean sessionListenerRegistrationBean() {
        ServletListenerRegistrationBean slrBean = new ServletListenerRegistrationBean();
        slrBean.setListener(new SessionListener());
        return slrBean;
    }
}