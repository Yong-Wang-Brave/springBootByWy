package com.wy.demo.读写分离;

import java.lang.annotation.*;

/**
 * @Author ys 2021/4/8
 * @description
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
  String value() default "master";
}
