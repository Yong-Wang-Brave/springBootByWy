package com.wy.demo.config;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.google.common.collect.Maps;
import com.wy.demo.lightspot.UnitedReturn.ResponseResultInterceptor;
import com.wy.demo.全局日志id.TraceInterceptor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfiguration extends CachingConfigurerSupport {

    @Autowired
    RedisProperties redisProperties;

    @Bean
    public CacheManager redisCacheConfiguration(RedisConnectionFactory connectionFactory){
        HashMap<String, RedisCacheConfiguration> config = Maps.newHashMap();
        GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        CacheModelDefiend[] values = CacheModelDefiend.values();
        for (CacheModelDefiend value : values) {
            config.put(value.getName(), RedisCacheConfiguration.defaultCacheConfig(Thread.currentThread().getContextClassLoader())
                    .entryTtl(Duration.ofMillis(value.getTtl()))
                    .disableCachingNullValues()
                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(genericFastJsonRedisSerializer)));
        }
        return RedisCacheManager.builder(connectionFactory)
                .withInitialCacheConfigurations(config)//按cacheName单独设置
                .build();
    }

    @Bean(name= "redisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        //序列化
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        //value值的序列化采用fastJosnRedisSerializer
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        //key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(){
        Config config =new Config();
      String prefix =  redisProperties.isSsl()?"rediss://": "redis://";
       if (null != redisProperties.getSentinel()){
           //哨兵模式
           SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
           sentinelServersConfig
                   .setMasterName(redisProperties.getSentinel().getMaster())
                   .setDatabase(redisProperties.getDatabase())
                   //.setConnectTimeout((int)redisProperties.getConnectTimeout().toMillis())
                   .setSubscriptionsPerConnection(5000);
           if (StrUtil.isNotEmpty(redisProperties.getPassword())) {
               sentinelServersConfig.setPassword(redisProperties.getPassword());
           }
           List<String> nodes = redisProperties.getSentinel().getNodes();
           for (String node : nodes) {
               sentinelServersConfig.addSentinelAddress(prefix.concat(node));
           }
       }else if (null != redisProperties.getCluster()) {
       //集群模式
           ClusterServersConfig clusterServersConfig = config.useClusterServers();
           List<String> nodes = redisProperties.getCluster().getNodes();
           if (StrUtil.isNotEmpty(redisProperties.getPassword())) {
               clusterServersConfig.setPassword(redisProperties.getPassword());
           }
           for (String node : nodes) {
               clusterServersConfig.addNodeAddress(prefix.concat(node));
           }
       }else if(StrUtil.isNotEmpty(redisProperties.getHost())){
            //单节点
           String address =prefix +redisProperties.getHost()+
                   ":"+redisProperties.getPort();
           SingleServerConfig singleServerConfig = config.useSingleServer();
           singleServerConfig.setAddress(address)
                   .setDatabase(redisProperties.getDatabase())
               //    .setConnectTimeout((int)redisProperties.getConnectTimeout().toMillis())
                   .setSubscriptionsPerConnection(5000);
           if (StrUtil.isNotEmpty(redisProperties.getPassword())) {
               singleServerConfig.setPassword(redisProperties.getPassword());
       }

    }
       //使用超时时间 30000L
       // config.setLockWatchdogTimeout();
       return Redisson.create(config);

}


@Override
@Bean
public KeyGenerator keyGenerator() {
     return (target,method,params) ->{
           Class<?> aClass =  target.getClass();
         Package aPackage = aClass.getPackage();
         String classString = aClass.toGenericString();
         String methodName  = method.getName();

         HashMap<Object,Object> dataMap = Maps.newHashMap();
          dataMap.put("package",aPackage);
          dataMap.put("class",classString);
          dataMap.put("method",methodName);
         for (int i = 0; i < params.length ; i++) {
             dataMap.put(String.valueOf(i),params[i]);
         }
          String dataString = JSON.toJSONString(dataMap);
          return DigestUtils.sha256Hex(dataString);
     }  ;
}
}