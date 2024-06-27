package com.zk.middleware.mideleware.aop;

import com.alibaba.fastjson.JSON;
import com.zk.middleware.mideleware.annotation.DoWhiteList;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 白名单拦截 DoJoinPoint
 */
@Aspect
@Component
public class DoJoinPoint {

    private Logger logger = LoggerFactory.getLogger(DoJoinPoint.class);

    @Resource
    private String whiteListConfig;

    @Pointcut("@annotation(com.zk.middleware.mideleware.annotation.DoWhiteList)")
    public void aopPoint(){}


    @Around("aopPoint()")
    public Object doRouter(ProceedingJoinPoint jp) throws Throwable {

        // 1.获取内容
        Method method = getMethod(jp);
        DoWhiteList whiteList = method.getAnnotation(DoWhiteList.class);

        // 2.获取指定属性字段值
        String keyValue = getFiledValue(whiteList.key(), jp.getArgs());
        logger.info("白名单-中间件处理方法：{} 属性字段：{} ",method.getName(),keyValue);
        if (null == keyValue || "".equals(keyValue)) {
            return jp.proceed();
        }

        String[] split = whiteListConfig.split(",");

        // 白名单过滤
        for (String str : split) {
            if (keyValue.equals(str)){
                return jp.proceed();
            }
        }

        // 3.拦截
        return retuanObject(whiteList, method);
    }


    /**
     * 1.获取内容
     * @param jp
     * @return
     * @throws NoSuchMethodException
     */
    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        // 1.从对象中获取方法的签名信息，Signature是JoinPoint的一个属性，表示连接点的签名
        Signature sig = jp.getSignature();
        // 2.将获取到的签名信息转换为MethodSignature类型，MethodSignature是Signature的一个子类，用于表示方法的签名信息。
        MethodSignature methodSignature = (MethodSignature) sig;
        // 3.- 通过JoinPoint对象的getTarget()方法获取目标对象（被代理的对象）的Class对象，然后调用getMethod()方法获取指定方法名和参数类型的Method对象。
        //   - methodSignature.getName()：获取方法的名称。
        //   - methodSignature.getParameterTypes()：获取方法的参数类型数组。
        //   - 最终返回获取到的Method对象，表示目标方法。
        Method result = jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        return result;
    }


    /**
     * 2.获取字段值
     * 这段代码的作用是从参数数组中遍历对象，获取指定字段的值。如果找到第一个非空字段值，则返回该值；
     * 如果参数数组长度为1且无法获取字段值，则返回第一个参数对象的字符串表示形式
     * @param filed 指定字段
     * @param args 参数数组
     * @return
     */
    private String getFiledValue(String filed, Object[] args) {
        String filedValue = null;
        for (Object arg : args) {
            try {
                if (null == filedValue || "".equals(filedValue)){
                    filedValue = BeanUtils.getProperty(arg,filed);
                } else {
                    break;
                }
            } catch (Exception e) {
                if (args.length == 1) {
                    return args[0].toString();
                }
            }
        }
        return filedValue;
    }

    /**
     * 3.用于根据@DoWhiteList注解配置的返回JSON字符串，返回相应的对象
     * @param whiteList 白名单
     * @param method 方法
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private Object retuanObject(DoWhiteList whiteList, Method method) throws InstantiationException, IllegalAccessException {
        // 1.获取目标方法的返回类型，通过Method对象的getReturnType()方法获取。
        Class<?> returnType = method.getReturnType();
        // 2.获取@DoWhiteList注解中配置的returnJson字符串，表示要返回的JSON数据。
        String returnJson = whiteList.returnJson();
        if ("".equals(returnJson)) {
            // 如果returnJson为空，使用反射创建返回类型的实例并返回
            return returnType.newInstance();
        }
        // 3.如果returnJson不为空，使用JSON工具解析returnJson字符串为返回类型的对象并返回
        return JSON.parseObject(returnJson,returnType);

    }

}

