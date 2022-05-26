package com.wy.demo.自定义注解.demo1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//作用1 作为aop的切点
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AOPAnnotation {
}
