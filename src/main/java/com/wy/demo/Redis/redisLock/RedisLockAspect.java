package com.wy.demo.Redis.redisLock;


import cn.hutool.core.util.StrUtil;
import com.wy.demo.Exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j

@RequiredArgsConstructor
@Aspect
@Component
public class RedisLockAspect {

    private final RedissonClient redissonClient;
    @Pointcut("@annotation(com.wy.demo.Redis.redisLock.RedisLock)")
    public void redisLock(){}
    @Around("redisLock()")
    public Object doRedisLock(ProceedingJoinPoint joinPoint) throws Throwable{
        //从方法参数获取锁的key
        MethodSignature signature =    (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RedisLock lock = method.getAnnotation(RedisLock.class);
        String prefix = lock.prefix();
        String spelString = lock.lockKey();
        //通过joinpoint获取被注解的方法
        if (StrUtil.isBlank(prefix)|| StrUtil.isBlank(spelString)) {
            log.error("生成redis key 失败，前缀和表达式为空！");
            throw  new ServiceException("生成redis key 失败，前缀和表达式为空！");
        }
        String value =generateKeyBySpel(spelString,joinPoint);
        String key =prefix + ":" +value;
        RLock rLock = redissonClient.getLock(key);
        RedissonRedLock redissonRedLock = new RedissonRedLock(rLock);
        boolean locked = false;
        try {
            locked = redissonRedLock.tryLock(lock.waitSeconds(), TimeUnit.SECONDS);
            if (locked) {
                log.info(Thread.currentThread().getName()+"拿到锁了，key:{}",key);
                return joinPoint.proceed();
            }else{
                log.error("获取redis锁失败！");
                throw  new ServiceException("获取redis锁失败");
            }
        } catch (InterruptedException e) {
           throw new ServiceException(e);
        } finally {
            if (locked) {
                redissonRedLock.unlock();
                log.info(Thread.currentThread().getName()+"释放锁了，key:{}",key);
            }
        }

    }

    private String generateKeyBySpel(String spelString, ProceedingJoinPoint joinPoint) {
        try {
            if (!spelString.startsWith("#")) {
                return spelString;
            }

            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            //创建解析器
            SpelExpressionParser parser = new SpelExpressionParser();
            //获取表达式
            Expression expression = parser.parseExpression(spelString);
            //设置解析上下文（有哪些占位符，以及每种站位符的值）
            EvaluationContext context = new StandardEvaluationContext();
            //获取参数值
            Object[] args = joinPoint.getArgs();
            //获取运行时参数的名称
            DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
            String[] parameterNames = discoverer.getParameterNames(method);
            for (int i = 0; i < Objects.requireNonNull(parameterNames).length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
            //解析，获取替换后的结果
            return Objects.requireNonNull(expression.getValue(context)).toString();

        } catch (Exception e) {
            log.error("生成redis key发生异常", e);
            throw new ServiceException("生成redis key发生异常");
        }
    }
}
