package com.wy.demo.自定义注解.castAsString;


import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnabledJsonParse {
}
