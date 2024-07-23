package com.wy.demo.xielaoshi.xianchengchigongjulei;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolUtils {
    private static final Logger log = LoggerFactory.getLogger(ThreadPoolUtils.class);
    private static volatile ConcurrentHashMap<String, ThreadPoolExecutor> executorMap = new ConcurrentHashMap<>();
    public static final String POOL_DEAULT_NAME = "pool-default";

    public ThreadPoolUtils() {

    }

    public static ThreadPoolExecutor getInstance() {
        return get((String) null);
    }

    public static ThreadPoolExecutor getInstance(String name) {
        return get(name);
    }

    public static ThreadPoolExecutor get(String name) {
        if (name == null) {
            name = POOL_DEAULT_NAME;
        }
        ThreadPoolExecutor executor = (ThreadPoolExecutor) executorMap.get(name);
        return executor != null ? executor : register(name);
    }

    public static ThreadPoolExecutor register(String name) {
        ThreadPoolExecutor executor = null;
        Class var2= ThreadPoolUtils.class;
        synchronized(ThreadPoolUtils.class) {
            executor = (ThreadPoolExecutor) executorMap.get(name);
            if (executor != null) {
                return executor;
            }else{
                ThreadPoolConfigUtils config = ThreadPoolConfigUtils.getInstance(name);
                executor =     new ThreadPoolExecutor(config.getCorePoolSize(), config.getMaxPoolSize(), (long)config.getKeepAliveTime(), TimeUnit.MILLISECONDS
                        , new LinkedBlockingQueue(config.getBlockongQueueSize()),new ThreadPoolUtils.CommonThreadFactory(name));
           executorMap.put(name, executor);
           return  executor;

            }
        }
    }

    private static class CommonThreadFactory  implements ThreadFactory {
       private static final AtomicInteger poolNumber = new AtomicInteger(1);
       private final ThreadGroup group;
       private final AtomicInteger threadNumber = new AtomicInteger(1);
       private final String namePrefix;

         CommonThreadFactory(String poolName) {
            SecurityManager s = System.getSecurityManager();
            this.group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = poolName + "-thread-";
        }
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(this.group, r,this.namePrefix+this.threadNumber.getAndIncrement(), 0L);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

}
