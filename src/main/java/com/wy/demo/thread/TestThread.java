
package com.wy.demo.thread;

import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class TestThread {

@Autowired
ThreadPoolConfig1 threadPoolConfig;


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

