package com.wy.demo.aop;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MqsHandler {
    private static  ThreadLocal<String> traceId =new TransmittableThreadLocal<>();
    public static String  getTraceId(){return traceId.get();}
@Pointcut("execution(* com.wy.demo.controller.Service.Impl.SortCourseServiceImpl.findSortCourseByDTO(..))")
public void mqAspect(){}

    @Before("mqAspect()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        traceId.set("uuid");
    }
    @AfterReturning(returning = "ret",pointcut = "mqAspect()")
    public void doAfterReturning(JoinPoint joinPoint,Object ret) throws  Throwable{
        traceId.remove();
    }

    @AfterThrowing(pointcut = "mqAspect()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,Throwable e) throws  Throwable{
        traceId.remove();
    }

}
