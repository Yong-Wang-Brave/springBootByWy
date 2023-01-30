package com.wy.demo.自定义注解.demo3;

import com.wy.demo.mybatiesInterceptor.FillType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE,ElementType.FIELD})
public @interface Encrypt {
    String columnName();
    String original();
    String tableName();
    FillType fillType();
}
