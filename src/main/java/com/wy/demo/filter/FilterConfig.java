package com.wy.demo.filter;

import com.wy.demo.config.XSS.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import javax.servlet.DispatcherType;

//@Configuration
public class FilterConfig {
    /**
     * 配置过滤器，这里过滤器主要是对返回值做后继处理
     *
     * @return
     */
/*    @Bean
    public FilterRegistrationBean someFilterRegistration()
    {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ResponseFilter());// 配置一个返回值加密过滤器
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("responseFilter");
        return registration;
    }*/

     //@Bean
    public FilterRegistrationBean<XssFilter>  xssFilterFilterRegistrationBean(){
        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return  registration;
    }

    @Bean
    @Order(1)
    public FilterRegistrationBean<LogFilter> logFilter(){
        LogFilter logFilter = new LogFilter();
        FilterRegistrationBean<LogFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(logFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
@Bean
@Order(2)
public FilterRegistrationBean<UserFilter> userFilter(){
    UserFilter userFilter = new UserFilter();
    FilterRegistrationBean<UserFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
    filterFilterRegistrationBean.setFilter(userFilter);
    filterFilterRegistrationBean.addUrlPatterns("/*");
    return filterFilterRegistrationBean;

}

}
