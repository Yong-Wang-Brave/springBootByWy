package com.wy.demo.shejimoshi.celuemoshi2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 支付上下文环境对象
 *
 * @Author WDYin
 * @Date 2022/4/16
 **/
@Component
public class PayContext {

    /**
     * payMap容器存储支付类型和支付类的映射关系
     * 此map可以使用框架Guava或者Caffine
     */
    private final Map<String, Pay> payMap = new ConcurrentHashMap<>();

    /**
     * 注入spring容器对象
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 初始化payMap容器
     * PostConstruct:该注解的方法在项目启动的时候执行这个方法，
     * 也就是在spring容器启动的时候执行.
     */
    @PostConstruct
    private void init() {
        /**
         * 从spring容器中获取所有pay类型的Bean
         */
        Map<String, Pay> beanMap = applicationContext.getBeansOfType(Pay.class);
        /**
         * 存入map集合
         */
        //beanMap.forEach((key, value) -> payMap.put(value.getPayType().getCode(), value));

        for (String key : beanMap.keySet()) {
            System.out.println("key= " + key + " and value= " + beanMap.get(key));
            if (!key.equals("pay")) {
                payMap.put(beanMap.get(key).getPayType().getCode(), beanMap.get(key));

            }

        }
    }

    /**
     * 使用时调用该支付方法即可
     *
     * @param payType
     */
    public Pay pay(String payType) {
       return  payMap.get(payType);
    }
}
