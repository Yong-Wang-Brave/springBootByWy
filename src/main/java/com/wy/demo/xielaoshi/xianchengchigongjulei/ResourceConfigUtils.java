package com.wy.demo.xielaoshi.xianchengchigongjulei;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Configuration
public class ResourceConfigUtils {

    @Resource
    protected Environment environment;

    public ResourceConfigUtils(){}

    public String getValue(String key){return (String) this.environment.getProperty(key,String.class);}

    public <T> T getValue(String key,Class<T> clazz){return (T) this.environment.getProperty(key,clazz);}

}
