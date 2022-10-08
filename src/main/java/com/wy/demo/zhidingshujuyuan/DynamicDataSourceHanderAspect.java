package com.wy.demo.zhidingshujuyuan;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Order(2)
@Aspect
@EnableAspectJAutoProxy
@Component
public class DynamicDataSourceHanderAspect {


    @Before("@annotation(com.wy.demo.zhidingshujuyuan.DynamicDataSourceSwitch)")
    public void doBefore(JoinPoint joinPoint){
       Method method= ((MethodSignature)joinPoint.getSignature()).getMethod();
        DynamicDataSourceSwitch annotationClass = method.getAnnotation(DynamicDataSourceSwitch.class);
        if (null==annotationClass) {
            annotationClass = joinPoint.getTarget().getClass().getAnnotation(DynamicDataSourceSwitch.class);
            if (null==annotationClass) {
                return;
            }
        }

        String dataSource = annotationClass.value();
        DataSourceHolder.setDataSourceType(dataSource);

        log.info("aspect data source className: "+joinPoint.getTarget().getClass().getName()+",methodName"+method.getName()+",dataSource:"+dataSource==""?"默认数据源ORACLE":dataSource);

    }
    @After("@annotation(com.wy.demo.zhidingshujuyuan.DynamicDataSourceSwitch)")
    public void after(JoinPoint point){
        DataSourceHolder.clearDataSourceType();
    }


}
