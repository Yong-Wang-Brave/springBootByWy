package com.wy.demo.spring;

import com.wy.demo.spring.DTO.InParam;
import com.wy.demo.spring.service.BikeImpl;
import com.wy.demo.spring.service.CarServiceImpl;
import com.wy.demo.spring.service.TypeService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 更优雅的根据类型获取bean的方式的实现
 */
@Component
public class TypeFactory implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Map<String, TypeService> typeMap = new HashMap<>();

    //spring初始化bean有两种方式
    //第一：实现InitializingBean接口，继而实现afterPropertiesSet的方法
    //反射原理，配置文件使用init-method标签直接注入bean
    @Override
    public void afterPropertiesSet() throws Exception {
        //不同类型的订单查看
        queryOrderDetailByOrderType();
    }

    private void queryOrderDetailByOrderType() {
        typeMap.put("bike", getBeanType1(BikeImpl.class));
        typeMap.put("car", getBeanType1(CarServiceImpl.class));
    }

    private <T> T getBeanType1(Class<T> type) {
        return applicationContext.getBean(type);
    }

//从已有的spring上下文取得已实例化的bean,通过AppliacationContextAware接口实现
    //当一个类实现了这个接口（ApplicationCntextAware）之后，这个类就可以获取到spring上下文中所有的bean，换句话说，就是这个类
    //可以直接获取spring配置文件中，所有引用到bean的名称，以及bean的实例
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public TypeService getType(String type) {
        return typeMap.get(type);
    }

    }

