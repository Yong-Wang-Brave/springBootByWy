package com.wy.demo.spring.proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

@Component
public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws Exception {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] classes = {IUserDao.class};
        InvocationHandler handler = (proxy, method, args) -> "你被代理了 " + method.getName();

        return (IUserDao) Proxy.newProxyInstance(classLoader, classes, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }



}