package com.wy.demo.Redis;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLockTest2 {

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

   @Resource(type = Redis.class )
    private  Redis redis1;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

@Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


//该代码的意思是 半个小时内一个手机号只会推送一次，利用set的特性判断；set集合里面没数据返回1，有数据（已经set过了会返回1）
// 该半个小时key过期的时间是一天，应该是方便查看日志，一天内该时段，改手机号是否被推送过
    @Scheduled(cron = "1 35 * * * ?")
    public void push() {
        System.out.println(redis1.getClass().toString());
        LocalDateTime now = LocalDateTime.now();
        lock(now);
    }

    void lock(LocalDateTime taskTime) {
    int minute =taskTime.getMinute();
        if (( 0<minute && minute <=30)) {
             taskTime = taskTime.withMinute(29).withSecond(0);
        }else{
            taskTime = taskTime.withMinute(59).withSecond(0);
        }
        String taskDate = taskTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime endTime1 = taskTime.plusMinutes(30);
        String startTime = taskTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String endTime = endTime1.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
       //:会导致存入redis的key值是文件夹
        String key = String.format("%s:lock:push:message:taskDate:%s:startTime:%s:endTime:%s", "wyylock", taskDate, startTime, endTime);
        String redisLockValue = IdUtil.simpleUUID();

             //rt.opsForSet().add("key", set) //set是一个集合对象  利用set的特点 第二次存就是0
            Long addResult = stringRedisTemplate.opsForSet().add(key, StrUtil.EMPTY);
            if (Objects.equals(addResult, RedisUtils.SUCCESS)) {
                stringRedisTemplate.expire(key,1L, TimeUnit.DAYS);
            }

        List<String> strings = Arrays.asList("110", "119", "120");
            strings.stream().forEach((o)->threadPoolTaskExecutor.execute(new PushMessageThread(stringRedisTemplate,key,o)));

    }


}
