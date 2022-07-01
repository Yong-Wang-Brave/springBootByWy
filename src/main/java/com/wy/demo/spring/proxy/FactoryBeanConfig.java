package com.wy.demo.spring.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanConfig {

    @Bean(name = "ProxyBeanFactory")
    public ProxyBeanFactory UserDao() {
        return new ProxyBeanFactory();
    }
}
