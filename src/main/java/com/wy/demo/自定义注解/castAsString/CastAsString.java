package com.wy.demo.自定义注解.castAsString;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CastAsString {
     String[] paths() default {};
    String name() default "castAsStringResponseJsonHandler";
}
