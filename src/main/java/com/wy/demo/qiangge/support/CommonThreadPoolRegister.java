package com.wy.demo.qiangge.support;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@Configuration
public class CommonThreadPoolRegister {
    @Bean
    @Primary
    public ExecutorService commonTaskExecutor(){
        return Executors.newFixedThreadPool(60);
    }
}
