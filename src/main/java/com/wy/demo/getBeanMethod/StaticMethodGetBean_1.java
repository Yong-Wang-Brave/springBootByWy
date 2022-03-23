package com.wy.demo.getBeanMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
import javax.annotation.PostConstruct;
 
/**
 * springboot静态方法获取 bean 的三种方式(一)
 * @author: clx
 * @version: 1.1.0
 */
@Component
public class StaticMethodGetBean_1 {
 
    @Autowired
    private AutoMethodDemoService autoMethodDemoService;
 
    @Autowired
    private static AutoMethodDemoService staticAutoMethodDemoService;
 
    @PostConstruct
    public void init() {
        staticAutoMethodDemoService = autoMethodDemoService;
    }
 
    public static String getAuthorizer() {
        return staticAutoMethodDemoService.test();
    }
}