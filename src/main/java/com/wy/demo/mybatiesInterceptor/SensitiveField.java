package com.wy.demo.mybatiesInterceptor;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SensitiveField {
   //表字段
    String columnName();
    //待加密字段
    String original();
    //加密类型
     FillType fillType();
}
