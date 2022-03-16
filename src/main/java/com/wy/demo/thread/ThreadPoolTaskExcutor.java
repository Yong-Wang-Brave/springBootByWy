
package com.wy.demo.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

//说白了 达到最大线程了，就会执行拒绝策略
@Configuration
    public class ThreadPoolTaskExcutor {
        @Bean("taskExector")
        public Executor taskExector() {

            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            int i = Runtime.getRuntime().availableProcessors();//获取到服务器的cpu内核
            executor.setCorePoolSize(5);//核心池大小
            executor.setMaxPoolSize(10);//最大线程数
            executor.setQueueCapacity(20);//队列程度
            executor.setKeepAliveSeconds(1000);//线程空闲时间
            executor.setThreadNamePrefix("tsak-asyn");//线程前缀名称
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//配置拒绝策略
            return executor;
        }

    }
