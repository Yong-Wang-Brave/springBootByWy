package com.wy.demo.xielaoshi.xianchengchigongjulei;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPoolConfigUtils {
    private static final Logger log = LoggerFactory.getLogger(ThreadPoolConfigUtils.class);
    private static final String THREAD_POOL = "thread_pool";
    private static final String SEPARATOR = ".";
    private static final String CORE_SIZE = "core-pool-size";
    private static final String MAX_SIZE = "max-pool-size";
    private static final String KEEP_TIME = "keep_alive_time";
    private static final String QUEUE_SIZE = "blockong-quene-size";
    private int corePoolSize = 5;
    private int maxPoolSize = 20;
    private int keepAliveTime = 3000;
    private int blockongQueueSize = 100;

    public static ThreadPoolConfigUtils getInstance() {
        return getInstance((String)null);
    }

    public static ThreadPoolConfigUtils getInstance(String name) {
        ThreadPoolConfigUtils configUtils = new ThreadPoolConfigUtils();
        configUtils.initConfig(name);
        return configUtils;
    }

    private void initConfig(String name) {
        ResourceConfigUtils resource = SpringApplicationContext.getBean(ResourceConfigUtils.class);
        Integer coreSize = (Integer) resource.getValue(this.getConfigKey(name, "core_pool_size"), Integer.class);
        Integer maxSize = (Integer) resource.getValue(this.getConfigKey(name, "max_pool_size"), Integer.class);
        Integer keepTime = (Integer) resource.getValue(this.getConfigKey(name, "keep_alive_time"), Integer.class);
        Integer queueSize = (Integer) resource.getValue(this.getConfigKey(name, "blocking_queue_size"), Integer.class);
        // 设置参数
        this.corePoolSize = coreSize == null ? this.corePoolSize : coreSize;
        this.maxPoolSize = maxSize == null ? this.maxPoolSize : maxSize;
        this.keepAliveTime = keepTime == null ? this.keepAliveTime : keepTime;
        this.blockongQueueSize = queueSize == null ? this.blockongQueueSize : queueSize;
        log.info("CORE_POOL_SIZE:{},MAX_POOL_SIZE:{},KEEP_ALIVE_TIME:{},BLOCKONG_QUENE_SIZE:{}", this.corePoolSize, this.maxPoolSize, this.keepAliveTime, this.blockongQueueSize);
    }

    private String getConfigKey(String name, String key) {
        return name != null && !name.equals("pool-deault") ? "thread_pool." + name + "." + key : "thread_pool." + key;
    }

    public ThreadPoolConfigUtils() {
    }

    public int getCorePoolSize() {
        return this.corePoolSize;
    }

    public int getMaxPoolSize() {
        return this.maxPoolSize;
    }

    public int getKeepAliveTime() {
        return this.keepAliveTime;
    }

    public int getBlockongQueueSize() {
        return this.blockongQueueSize;
    }

    public void setCorePoolSize(final int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public void setMaxPoolSize(final int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public void setKeepAliveTime(final int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public void setBlockongQueueSize(final int blockongQueueSize) {
        this.blockongQueueSize = blockongQueueSize;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof ThreadPoolConfigUtils)) {
            return false;
        } else {
            ThreadPoolConfigUtils other = (ThreadPoolConfigUtils) o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getCorePoolSize() != other.getCorePoolSize()) {
                return false;
            } else if (this.getMaxPoolSize() != other.getMaxPoolSize()) {
                return false;
            } else if (this.getKeepAliveTime() != other.getKeepAliveTime()) {
                return false;
            } else {
                return this.getBlockongQueueSize() != other.getBlockongQueueSize();
            }
        }
    }
        protected boolean canEqual(final Object other){
            return other instanceof ThreadPoolConfigUtils;
        }


        @Override
        public int hashCode () {

            int result = 1;
            result = result * 59 + this.getCorePoolSize();
            result = result * 59 + this.getMaxPoolSize();
            result = result * 59 + this.getKeepAliveTime();
            result = result * 59 + this.getBlockongQueueSize();
            return result;
        }


        @Override
        public String toString () {
            return "ThreadPoolConfigUtils{" +
                    "corePoolSize=" + corePoolSize +
                    ", maxPoolSize=" + maxPoolSize +
                    ", keepAliveTime=" + keepAliveTime +
                    ", blockongQueueSize=" + blockongQueueSize +
                    '}';
        }

    }