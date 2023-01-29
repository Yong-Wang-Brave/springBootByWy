package com.wy.demo.thread;

import com.wy.demo.lightPoint.tokenGetUserInfo.threadLocal.UserContext;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Configuration
public class ThreadPoolConfig {

    @Bean(value = "asyncServiceExecutor", destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor asyncServiceExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        threadPoolTaskExecutor.setCorePoolSize(5);
        //核心线程如处于空闲状态的话，超过一定时间（keepAliveTime）,就会销毁掉
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        //最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(10);
        //配置队列大小
        threadPoolTaskExecutor.setQueueCapacity(100);
        //加入装饰器
        threadPoolTaskExecutor.setTaskDecorator(new ContextCopyingDecorator());
        return  threadPoolTaskExecutor;
    }


    static class ContextCopyingDecorator implements TaskDecorator {
        @Override
        public Runnable decorate(Runnable runnable) {
            //主线程
            UserContext.UserInfo userInfo = UserContext.getUserInfo();
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            String traceId = MDC.get("traceId");
            return ()->{
                try {
                    //将变量重新放入子线程
                    UserContext.setUserInfo(userInfo);
                    RequestContextHolder.setRequestAttributes(attributes);
                    MDC.put("traceId",traceId);
                    runnable.run();
                } finally {
                    UserContext.remove();
                    RequestContextHolder.resetRequestAttributes();
                    MDC.clear();
                }
            };
        }
    }
}
