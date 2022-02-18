package com.wy.demo.Redis;

import cn.hutool.core.util.IdUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RedisLockTest {

    @Resource
    private RedisUtils redisUtils;

    @Scheduled(cron = "* * * * * ?")
    public void push() {
        LocalDateTime now = LocalDateTime.now();
        lock(now);
    }

    void lock(LocalDateTime taskTime) {

        String taskDate = taskTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime endTime1 = taskTime.plusMinutes(30);
        String startTime = taskTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String endTime = endTime1.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
       //:会导致存入redis的key值是文件夹
        String key = String.format("%s:lock:push:message:taskDate:%s:startTime:%s:endTime:%s", "wyylock", taskDate, startTime, endTime);
        String redisLockValue = IdUtil.simpleUUID();

        try {
            if (redisUtils.lock(key, redisLockValue, 30 * 60 * 60l)) {
                System.out.println("加锁成功");
            } else {
                System.out.println("加锁失败，任务处理中");
                return;
            }
        } catch (Exception e) {
            System.out.println("任务失败");
        } finally {
            redisUtils.unlock(key, redisLockValue);
        }

    }


}
