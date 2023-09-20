package com.wy.demo.config.feign;

import cn.hutool.core.util.StrUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 将请求头统一转发到服务方
 */
@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
     ServletRequestAttributes attributes =   (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
     if( attributes !=null){
         String reqId = attributes.getRequest().getHeader(Constants.REQUEST_ID);
         if (StrUtil.isEmpty(reqId)) {
             reqId= RandomStringUtils.random(16,Constants.SEEDS);
         }

         //将唯一ID添加到请求头中
         requestTemplate.header(Constants.REQUEST_ID,reqId);
         String userInfo =attributes.getRequest().getHeader(Constants.USER_INFO);
         if (StrUtil.isNotEmpty(userInfo)) {
             requestTemplate.header(Constants.USER_INFO,userInfo);
         }
     }else{
         String reqId = MDC.get(Constants.REQUEST_ID);
         if(StrUtil.isEmpty(reqId)){
             reqId=RandomStringUtils.random(16,Constants.SEEDS);
         }
         //将唯一的ID添加到请求头中
         requestTemplate.header(Constants.REQUEST_ID,reqId);
         String userInfo = MDC.get(Constants.USER_INFO);
         if (StrUtil.isNotEmpty(userInfo)) {
             try {
                 requestTemplate.header(Constants.USER_INFO, URLEncoder.encode(userInfo, StandardCharsets.UTF_8.toString()));
             } catch (UnsupportedEncodingException e) {
                 e.printStackTrace();
             }
         }


     }


    }
}
