package com.wy.demo.getBeanMethod;

import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: clx
 * @version: 1.1.0
 */
@RestController
public class TestController1 {
    /**
     * 方式二
     */
    @GetMapping("test2")
    public void method_2() {
        AutoMethodDemoService methodDemoService = Application.ac.getBean(AutoMethodDemoService.class);
        String test2 = methodDemoService.test2();
        System.out.println(test2);
    }
    /**
     * 方式三
     */
    @GetMapping("test3")
    public void method_3() {
        AutoMethodDemoService autoMethodDemoService = StaticMethodGetBean_3.getBean(AutoMethodDemoService.class);
        String test3 = autoMethodDemoService.test3();
        System.out.println(test3);
    }
}