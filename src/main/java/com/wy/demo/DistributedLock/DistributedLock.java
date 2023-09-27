package com.wy.demo.DistributedLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class DistributedLock {

    private static final String LOCK_KEY = "myLock";
    private static final long LOCK_EXPIRE_TIME = 20000L; // 锁的过期时间，单位毫秒
    private static final long SLEEP_INTERVAL = 100L; // 获取锁时的休眠间隔，单位毫秒

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean acquireLock() {
        long startTime = System.currentTimeMillis();
        try {
            while (true) {
                // 尝试获取锁
                if (redisTemplate.opsForValue().setIfAbsent(LOCK_KEY, "locked")) {
                    // 获取锁成功
                    redisTemplate.expire(LOCK_KEY, LOCK_EXPIRE_TIME, TimeUnit.MILLISECONDS);
                    return true;
                }

                // 获取锁失败，进行休眠
                Thread.sleep(SLEEP_INTERVAL);

                // 判断是否超时
                long currentTime = System.currentTimeMillis();
                System.out.println("时间间隔"+(currentTime - startTime) );
                if (currentTime - startTime > LOCK_EXPIRE_TIME) {
                    return false;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public void releaseLock() {
        redisTemplate.delete(LOCK_KEY);
    }
}