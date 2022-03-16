
package com.wy.demo.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestThread {

@Autowired
ThreadPoolConfig   threadPoolConfig;


@Scheduled(cron="30 37 * * * ?")
        void test(){
        this.threadTest();
    }
    int  i = 0;
    private  void threadTest() {

        for (   i = 0; i <1000 ; i++) {
            threadPoolConfig.threadPoolTaskExecutor().execute(new www(i));
        }

    }


}

