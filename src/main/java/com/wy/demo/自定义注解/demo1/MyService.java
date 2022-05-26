package com.wy.demo.自定义注解.demo1;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    @AOPAnnotation	// 标注
    public void doService() {
        System.out.println("service 方法执行... ...");
    }
}
