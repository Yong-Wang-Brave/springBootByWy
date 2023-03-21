package com.wy.demo.自定义注解.demo4;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对输入的字符串进行过滤的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Repalce {
    String source() default  "";
    String target() default  "";
}
