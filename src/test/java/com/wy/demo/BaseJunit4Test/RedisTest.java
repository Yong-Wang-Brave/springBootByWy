package com.wy.demo.BaseJunit4Test;

import com.wy.demo.Redis.RedisUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class RedisTest extends BaseJunit4Test {
    @Autowired
    RedisUtils redisLockUtil;

    @Test
    public void testRedis() {
        boolean lock = redisLockUtil.lock("customerId", "333", 100,TimeUnit.SECONDS);
        System.out.println(lock);
    }


}
