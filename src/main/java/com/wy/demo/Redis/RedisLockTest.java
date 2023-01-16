package com.wy.demo.Redis;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

//@Component
public class RedisLockTest {

    @Autowired
    private RedisUtils redisUtil;
//   @Autowired
//   private Redis redis1;
//    @Autowired
//    private Redis redis2;
/*    @Autowired
    @Qualifier(value = "redis1")
    private  Redis redis3;*/
    @Resource
    private  Redis redis2;

   @Resource(type = com.wy.demo.Redis.Redis.class )
    private  Redis redis1;







    @Scheduled(cron = "* * * * * ?")
    public void push() {
        System.out.println(redis1.getClass().toString());
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
            if (redisUtil.lock(key, redisLockValue, 30 * 60 * 60L,TimeUnit.SECONDS)) {
                System.out.println("加锁成功");
            } else {
                System.out.println("加锁失败，任务处理中");
                return;
            }
        } catch (Exception e) {
            System.out.println("任务失败");
        } finally {
            redisUtil.unlock(key, redisLockValue);
        }

    }


}
