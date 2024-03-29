package com.wy.demo.过滤器拦截器监听器.FilterRegistrationBean;

import cn.hutool.extra.spring.SpringUtil;
import com.wy.demo.Exception.headToSession.FilterAddToSession;
import com.wy.demo.lightPoint.tokenGetUserInfo.SecurityFilter2;
import com.wy.demo.lightPoint.tokenGetUserInfo.threadLocal.UserContextInterceptor;
import com.wy.demo.全局日志id.TraceLogInterceptor;
import com.wy.demo.自定义注解.demo2.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Configuration
//bean实现拦截过滤器（可以实现登录校验）
public class WebConfigSecurity implements WebMvcConfigurer {

    @Autowired
    private UserContextInterceptor userContextInterceptor;

    @Bean
    public  TraceLogInterceptor initTraceLogInterceptor(){
        return  new TraceLogInterceptor();
    }

    @Bean
    public UserContextInterceptor initUserContextInterceptor(){
        return  new UserContextInterceptor();
    }

    @Resource
    private MessageSource messageSource;

        // 重写WebMvcCOnfigurer的addInterceptors方法
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            // 注册AuthInterceptor拦截器
            registry.addInterceptor(new AuthInterceptor());
            registry.addInterceptor(userContextInterceptor).addPathPatterns("**/*.*");/*.addPathPatterns("/runHealth/healthManage/**");*/
            registry.addInterceptor(initTraceLogInterceptor()).addPathPatterns("**/*.*");/*.addPathPatterns("/runHealth/healthManage/**");*/
            registry.addInterceptor(initUserContextInterceptor()).addPathPatterns("**/*.*").addPathPatterns("/a/**");


        }


    @Bean
    public FilterRegistrationBean<SecurityFilter> registTest2(){
        SecurityFilter securityFilter =new SecurityFilter();
       SpringUtil.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(securityFilter);
        FilterRegistrationBean<SecurityFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(securityFilter);//注册自定义过滤器
       bean.addUrlPatterns("/testSecurity/*");//过滤所有的路径
        bean.setOrder(1);//优先级，越低越优先
        return bean;}

    @Bean
    public FilterRegistrationBean<SecurityFilter2> registTest3(){
        SecurityFilter2 securityFilter =new SecurityFilter2();
        SpringUtil.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(securityFilter);
        FilterRegistrationBean<SecurityFilter2> bean = new FilterRegistrationBean<>();
        bean.setFilter(securityFilter);//注册自定义过滤器
        bean.addUrlPatterns("/runHealth/healthManage/*");//过滤所有的路径
        return bean;
    }


    @Bean
    public FilterRegistrationBean<FilterAddToSession> SecurityFilterRegister(){
        FilterAddToSession securityFilter =new FilterAddToSession();
        SpringUtil.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(securityFilter);
        FilterRegistrationBean<FilterAddToSession> bean = new FilterRegistrationBean<>();
        bean.setFilter(securityFilter);//注册自定义过滤器
        bean.addUrlPatterns("/add/*");//过滤所有的路径
        return bean;
    }



    @Override
    @NotNull
    @Bean
    public Validator getValidator(){
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);
        return  validator;

    }


}
