package com.wy.demo.config.feign;

import cn.hutool.core.util.StrUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 将请求头统一转发到服务方
 */
@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
     ServletRequestAttributes attributes =   (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
     assert attributes !=null;
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

    }
}
