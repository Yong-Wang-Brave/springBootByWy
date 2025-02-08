package com.wy.demo.自定义注解.castAsString;


import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnabledJsonParse
public @interface JsonParser {
    String[] paths() default {};
    String name() default "";
}
