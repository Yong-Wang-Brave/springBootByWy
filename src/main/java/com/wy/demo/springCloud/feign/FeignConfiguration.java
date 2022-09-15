package com.wy.demo.springCloud.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

//将请求头参数一并转发到服务方  feighn的专属拦截器
@Configuration
public class FeignConfiguration implements RequestInterceptor {


  /*  @Bean("restTemplate")
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                MediaType.TEXT_HTML,
                MediaType.TEXT_PLAIN));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        return restTemplate;
    }*/
    @Override
    public void apply(RequestTemplate requestTemplate) {

        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        assert  attributes !=null;
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        byte[] body = requestTemplate.body();
        if ((headerNames!=null)) {
            while (headerNames.hasMoreElements()) {
                String name=headerNames.nextElement();
                String value=request.getHeader(name);
                requestTemplate.header(name,value);
            }
        }
        requestTemplate.body(new String(body, StandardCharsets.UTF_8));

    }



}
