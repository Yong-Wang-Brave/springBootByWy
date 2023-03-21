package com.wy.demo.动态代理;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

public class ProxyUtils {


    public  static  Star  createProxy(SuperStar superStar){

       //参数一： 类
        //参数二 接口
        Star starProxy = (Star)Proxy.newProxyInstance(ProxyUtils.class.getClassLoader(), new Class[]{Star.class}, new InvocationHandler() {
            @Override  //回调方法
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                //代理对象要做的事情，会在这里写代码
                if (method.getName().equals("sing")) {
                    System.out.println("准备话筒，收钱20万");
                } else if (method.getName().equals("dance")) {
                    System.out.println("准备场地，收钱1000万");
                }
                return method.invoke(superStar, objects);
            }
        });
        return starProxy;
    }

}
