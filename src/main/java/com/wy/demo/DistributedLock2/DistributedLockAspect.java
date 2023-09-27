package com.wy.demo.DistributedLock2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DistributedLockAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Around("@annotation(distributedLock)")
    public Object applyLock(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {
        // 获取注解参数，作为锁的键
        String lockKey = distributedLock.value();

        // 尝试获取分布式锁
        boolean lockAcquired = false;
        try {
            lockAcquired = redisTemplate.opsForValue().setIfAbsent(lockKey, "locked");
            if (lockAcquired) {
                // 获取锁成功，执行业务逻辑
                return joinPoint.proceed();
            } else {
                // 获取锁失败，抛出异常或做其他处理
                throw new RuntimeException("Failed to acquire lock");
            }
        } finally {
            if (lockAcquired) {
                // 释放锁
                redisTemplate.delete(lockKey);
            }
        }
    }
}