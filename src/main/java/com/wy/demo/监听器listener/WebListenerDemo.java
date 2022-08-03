package com.wy.demo.监听器listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebListenerDemo implements ServletContextListener {
 
 
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("监听器初始化。。。。。。。。。。。。");
    }
 
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("监听器销毁。。。。。。。。。。。");
    }
}