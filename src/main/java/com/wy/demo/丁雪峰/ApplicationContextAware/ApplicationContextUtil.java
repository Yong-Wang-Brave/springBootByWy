package com.wy.demo.丁雪峰.ApplicationContextAware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 当一个类实现了这个接口之后，这个类就可以方便的获得ApplicationContext对象（spring上下文），
 * Spring发现某个Bean实现了ApplicationContextAware接口，Spring容器会在创建该Bean之后，
 * 自动调用该Bean的setApplicationContext（参数）方法，
 * 调用该方法时，会将容器本身ApplicationContext对象作为参数传递给该方法。
 * ————————————————
 * 版权声明：本文为CSDN博主「凌兮～」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_40093255/article/details/117317943
 * @author 凌兮
 * @date 2020/5/18 15:39
 * 全局上下文
 */
//主要是为了获取srping上下文。
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    /**
     * 通过name获取 Bean
     * @param name beanName
     * @return Object
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }



    public static <T> T getBean(Class<T> requiredType) {
        return (T) applicationContext.getBean(requiredType);
    }
}

