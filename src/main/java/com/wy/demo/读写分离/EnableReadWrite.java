package com.wy.demo.读写分离;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ReadWriteConfiguration.class)
public @interface EnableReadWrite {
}
