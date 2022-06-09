
package com.wy.demo.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestThread {

@Autowired
ThreadPoolConfig   threadPoolConfig;


//@Scheduled(cron="* * * * * ?")
        void test(){
        this.threadTest();
    }
    int  i = 0;
    private  void threadTest() {

        for (   i = 0; i <100 ; i++) {
            threadPoolConfig.threadPoolTaskExecutor().getThreadNamePrefix();
            System.out.println("wangyong"+ threadPoolConfig.threadPoolTaskExecutor().getActiveCount());
            threadPoolConfig.threadPoolTaskExecutor().execute(new www(i));
        }

    }


}

