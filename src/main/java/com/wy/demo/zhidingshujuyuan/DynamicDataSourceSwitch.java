package com.wy.demo.zhidingshujuyuan;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface DynamicDataSourceSwitch {
    String value() default   "master";
}
