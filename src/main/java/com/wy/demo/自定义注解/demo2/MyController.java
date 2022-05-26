package com.wy.demo.自定义注解.demo2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	// 该方法标注了@AuthAnnotation注解
    @AuthAnnotation
    @GetMapping("/with")
    public String doMethodWithAuthAnnotation() {
        System.out.println("标注了@AuthAnnotation注解的方法执行");
        return "doMethodWithAuthAnnotation";
    }

	// 该方法未标注@AuthAnnotation注解
    @GetMapping("/without")
    public String doMethodWithoutAuthAnnotation() {
        System.out.println("未标注@AuthAnnotation注解的方法执行");
        return "doMethodWithoutAuthAnnotation";
    }
}
