package com.wy.demo.shejimoshi.策略模式20220708;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MsgTypeHandler {

    MsgTypeEnum value();
}