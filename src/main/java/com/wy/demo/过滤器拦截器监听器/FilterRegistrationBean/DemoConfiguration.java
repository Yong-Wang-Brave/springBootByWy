package com.wy.demo.过滤器拦截器监听器.FilterRegistrationBean;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//测试过滤器的优先级
public class DemoConfiguration {
    @Bean
    public FilterRegistrationBean<Test1Filter>  registTest1(){
        FilterRegistrationBean<Test1Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new Test1Filter());//注册自定义过滤器
        bean.setName("filter1");//过滤器名
        bean.addUrlPatterns("/*");//过滤所有的路径
        bean.setOrder(1);//优先级，最顶级
        return bean;
    }

    @Bean
    public FilterRegistrationBean<Test2Filter>  registTest2(){
        FilterRegistrationBean<Test2Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new Test2Filter());//注册自定义过滤器
        bean.setName("filter2");//过滤器名
        bean.addUrlPatterns("/test/*");//过滤所有的路径
        bean.setOrder(6);//优先级，越低越优先
        return bean;
    }
}
