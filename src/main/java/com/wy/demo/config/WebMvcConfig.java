package com.wy.demo.config;


import com.wy.demo.lightspot.UnitedReturn.ResponseResultInterceptor;
import com.wy.demo.全局日志id.TraceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ResponseResultInterceptor()).addPathPatterns("/**");
        //全局id
        registry.addInterceptor(new TraceInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/error");
    }
/*//HandlerMethodArgumentResolver
    @Autowired
    UserArgumentResolver userArgumentResolver;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }*/
}
