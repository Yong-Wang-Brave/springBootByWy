package com.zk.middleware.mideleware.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* DoWhiteList是一个自定义注解。
* 它作用就是在需要使用到的白名单服务的接口上，
* 添加此注解并配置必要的信息。接口入参提取字段属性名称、拦截后的返回信息
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoWhiteList {

   String key() default "";

   String returnJson() default "";

}

