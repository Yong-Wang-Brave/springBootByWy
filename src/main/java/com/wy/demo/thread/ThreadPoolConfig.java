package com.wy.demo.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;
//好像始终是一个线程处理
@Configuration
public class ThreadPoolConfig {

    @Bean(name="threadPoolTaskExecutor")
    //只有加上bean注解才会被注入
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        //队列满，线程被拒绝执行策略;用调用者所在的线程来执行任务
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
/*        pool.setCorePoolSize(3);
        //关键步骤 最大线程数不满就不会执行决绝策略
  *//*      pool.setQueueCapacity(5);
        pool.setMaxPoolSize(10);*//*
        pool.setKeepAliveSeconds(1000);//线程空闲时间*/
        pool.initialize();
        return pool;

    }
}
