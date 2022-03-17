package com.wy.demo.Redis;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Objects;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class PushMessageThread implements  Runnable {
    private StringRedisTemplate stringRedisTemplate;
    private  String key ;
    private String phoneNumber;
    @Override
    public void run() {
        try {
            System.out.println("");
            Long add = stringRedisTemplate.opsForSet().add(key, phoneNumber);
            if (!Objects.equals(add, RedisUtils.SUCCESS)) {
                System.out.println("已被其他机器推送");
                return;
            }
            System.out.println("推送成功");
        } catch (Exception e) {
            System.out.println("推送失败");
        }
    }
}
