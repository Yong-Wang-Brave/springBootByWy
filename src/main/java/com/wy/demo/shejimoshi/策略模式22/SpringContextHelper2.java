package com.wy.demo.shejimoshi.策略模式22;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;
//https://blog.csdn.net/m0_67401606/article/details/124047896
@Service
public class SpringContextHelper2 extends ApplicationObjectSupport {
    
    
    //提供一个接口，获取容器中的Bean实例，根据名称(名称都是小写)获取
    public Object getBean(String beanName)
    {
        return getApplicationContext().getBean(beanName);
    }
    
}
