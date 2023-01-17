package com.wy.demo.lightspot.UnitedReturn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
//@ControllerAdvice
public class ResponseResultHandler  implements ResponseBodyAdvice<Object> {
    //标记名称
    public static final String  RESPONSE_RESULT_ANN="RESPONSE_RESULT_ANN";
    //是否请求包含了包装注解标记，没有就直接返回，不需要重写返回体
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        //判断请求是否有包装标记
        ResponseResult responseResultAnn     = (ResponseResult)request.getAttribute(RESPONSE_RESULT_ANN);
        return responseResultAnn==null?false:true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("进入返回体重写格式处理中。。。。");
        if ((o instanceof List)) {

            return Result2.sucess(o);
        }else{
            log.info("返回值异常 做包装处理中");

            return Result2.failure();
        }

    }
}
