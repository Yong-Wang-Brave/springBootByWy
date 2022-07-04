package com.wy.demo.shejimoshi.策略模式22;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 更优雅的根据类型获取bean的方式的实现
 *
 *
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
        //在springbean的生命周期中，实例化》生成对象》属性填充会进行afterPropertiesSet方法，
        // 这个方法可以用在一些特殊情况中，也就是某个对象的某个属性需要经过过外界得到，比如查询数据库等，
        // 这个时候可以利用spring的该特性，只需要实现InitializingBean即可。
        //不同类型的订单查看
        queryOrderDetailByOrderType();
    }

    private void queryOrderDetailByOrderType() {
        typeMap.put(ToolEnum.TOOL_BIKE.getCode(), getBeanType1(BikeImpl.class));
        typeMap.put(ToolEnum.TOOL_BIKE.getCode(), getBeanType1(CarServiceImpl.class));
    }

    /**
     * 得到已经实例化的对象
     * @param type
     * @param <T>
     * @return
     */
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

