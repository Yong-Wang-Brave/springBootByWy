package com.wy.demo.other.jianrong202205525;

import java.lang.annotation.*;
//注解 其实就是一个特殊标记，引入标记会触发相关的内容。
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GamePHP {
}