package com.wy.demo.xielaoshi.xianchengchigongjulei;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class SpringApplicationContext implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(SpringApplicationContext.class);
    private static ApplicationContext context;
    private static final String GET_BEAN_ERROR = "getBean Exception";

    public SpringApplicationContext() {
    }

    public static <T> T getBean(String name, Class<T> cls) {
        try {
            return context.getBean(name, cls);
        } catch (Exception var3) {
            return null;
        }
    }

    public static <T> T getBean(Class<T> cls) {
        try {
            return context.getBean(cls);
        } catch (Exception var2) {
            return null;
        }
    }

    public static Object getBean(String name) {
        try {
            return context.getBean(name);
        } catch (Exception var2) {
            return null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if ((null != applicationContext)) {
            Class var2 = SpringApplicationContext.class;
            synchronized (SpringApplicationContext.class) {
                context = applicationContext;
            }
        }
    }

}
