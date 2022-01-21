package com.wy.demo.mybatiesInterceptor;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SensitiveTable {
    String value();
}
