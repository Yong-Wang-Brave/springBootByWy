package com.wy.demo.spring.proxy;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

public class MainTest {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] classes = {IUserDao.class};
        InvocationHandler handler = (proxy, method, args1) -> "你被代理了 " + method.getName();

        IUserDao userDao = (IUserDao) Proxy.newProxyInstance(classLoader, classes, handler);

        String res = userDao.queryUserInfo();
        System.out.println(res);
        //logger.info("测试结果：{}", res);
    }

}
