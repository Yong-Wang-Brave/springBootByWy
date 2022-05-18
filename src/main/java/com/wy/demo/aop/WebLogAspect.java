package com.wy.demo.aop;

import cn.hutool.core.date.SystemClock;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class WebLogAspect {
    @Pointcut("execution(public * com..*Controller*.*(..))")
    public void webLog(){
    }
    @Around("webLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        long endTime=0L;
        Object result =null;
        long startTime= SystemClock.now();
        log.info("\n ======= aop start requestId:{}",startTime);
        try {
            //接口的返回结果
            result = joinPoint.proceed();
            endTime= SystemClock.now();
        } catch (Exception e) {
            log.error(e.toString(),e);
            result=e.toString();
            throw e;
        }finally {
            //处理完请求，打印请求内容+返回内容，勇try catch处理异常，不影响业务代码
            try {
                ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
                assert  attributes!=null;
                HttpServletRequest request = attributes.getRequest();
                Object[] args = joinPoint.getArgs();
                Object[] arguments = new Object[args.length];
                for (int i = 0; i < args.length; i++) {
                    if ((args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile)) {
                        continue;
                }
                    arguments[i]=args[i];
            }
                String resultStr;
                if (result instanceof ServletResponse) {
                    resultStr = String.valueOf(request.getClass());
                }else{
                    resultStr = JSON.toJSONString(result);
                }
                log.info("\n ==== requset and response  request_id:{}=====" +
                                "\n request_url  :{}" +
                                "\n http_method  :{}" +
                                "\n class_methodname  :{}" +
                                "\n request_ip  :{}" +
                                "\n descrpition  :{}" +
                                "\n request_param  :{}" +
                                "\n response_result  :{}" +
                                 "\n ==== aop log end  request_id:{}=================",
                        startTime, StrUtil.toString(request.getRequestURI()),request.getMethod(),
                        joinPoint.getSignature(), "ip",endTime !=0L?(endTime-startTime)+"ms":"执行异常！",
                        JSON.toJSONString(arguments),resultStr,startTime);
            } catch (Exception e) {
                log.error("record aop log error !",e);
            }
return result;

        }
    }



}
