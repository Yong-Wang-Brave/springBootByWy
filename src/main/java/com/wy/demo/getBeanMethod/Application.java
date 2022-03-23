package com.wy.demo.getBeanMethod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
/**
 * @author: clx
 * @version: 1.1.0
 * 具体博客地址：
 * https://blog.csdn.net/u012102536/article/details/123682329
 */
@SpringBootApplication
public class Application {
    public static ConfigurableApplicationContext ac;
    public static void main(String[] args) {
       ac = SpringApplication.run(Application.class, args);
    }
 
}