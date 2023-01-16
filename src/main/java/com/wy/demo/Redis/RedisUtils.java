package com.wy.demo.Redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    //解决存入redis的key与value 乱码的问题
    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    /**
     * LUA 解锁脚本，保证原子性,只所以判断相等，是自己的线程解锁自己的线程
     */
    private static final String UN_LOCK_LUA = "if redis.call('get',KEYS[1])== ARGV[1] then return redis.call('del',KEYS[1])" +
            " else return  0 end";
    public static final Long SUCCESS = 1L;
    /*lua延时脚本*/
    private static final String POSTPONE_LOCK_LUA = "if redis.call('get',KEYS[1]) ==ARGV[1] then return redis.call('expire',KEYS[1]," +
            "ARGV[2]) end";

    /**
     * 加锁，添加保护线程防止业务未执行完成锁过期自动释放
     *
     * @param key        锁key
     * @param value      锁的value
     * @param expireTime 过期时间
     * @return true 成功 false 失败
     */
    public boolean lock(String key, String value, long expireTime, TimeUnit unit) {
        Boolean locked = redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, unit);
        log.info("分布式锁加锁结果---K:{},V:{},r:{}", key, value, locked);
        return locked != null && locked;
    }

    /**
     * 解锁 保证原子性
     *
     * @param key   redis的key
     * @param value redis存储的value
     * @return
     */
    public boolean unlock(String key, String value) {
        DefaultRedisScript<Long> scrit = new DefaultRedisScript<>(UN_LOCK_LUA, Long.class);
        Long result = redisTemplate.execute(scrit, Collections.singletonList(key), value);
        log.info("分布式锁解锁结果---K:{}，v:{},r:{}", key, value, result);
        return Objects.equals(SUCCESS, result);
    }

    /**
     * 锁延时
     *
     * @param key
     * @param value
     * @return
     */
    public boolean postpone(String key, String value, long expireTime) {
        DefaultRedisScript<Long> scrit = new DefaultRedisScript<>(POSTPONE_LOCK_LUA, Long.class);
        Long result = redisTemplate.execute(scrit, Collections.singletonList(key), value, Math.toIntExact(expireTime));
        log.info("分布式锁延时结果---K:{}，v:{},r:{}", key, value, result);
        return Objects.equals(SUCCESS, result);
    }


}

