package com.wy.demo.Redis.redisLock;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisLock {

    /**
     * 锁key的前缀
     * @return
     */
    String prefix() default "";


    /**
     * 获取锁的key
     * @return
     */
    String lockKey() default "";


    /**
     * 等待获取锁的时间 默认10秒
     * @return
     */
    long waitSeconds() default 10;

}
