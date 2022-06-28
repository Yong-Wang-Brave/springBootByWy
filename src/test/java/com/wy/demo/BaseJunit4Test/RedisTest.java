package com.wy.demo.BaseJunit4Test;

import com.wy.demo.Redis.RedisUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisTest extends BaseJunit4Test{
    @Autowired
    RedisUtils redisUtils;

    @Test
    public void testRedis(){
        boolean lock = redisUtils.lock("customerId", "333", 100);
        System.out.println(lock);
    }
}
