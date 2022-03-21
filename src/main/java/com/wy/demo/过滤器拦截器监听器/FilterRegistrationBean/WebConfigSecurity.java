package com.wy.demo.过滤器拦截器监听器.FilterRegistrationBean;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//bean实现拦截过滤器（可以实现登录校验）
public class WebConfigSecurity {

    @Bean
    public FilterRegistrationBean<SecurityFilter> registTest2(){
        SecurityFilter securityFilter =new SecurityFilter();
       SpringUtil.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(securityFilter);
        FilterRegistrationBean<SecurityFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(securityFilter);//注册自定义过滤器
        bean.addUrlPatterns("/testSecurity/*");//过滤所有的路径
        bean.setOrder(1);//优先级，越低越优先
        return bean;
    }
}
