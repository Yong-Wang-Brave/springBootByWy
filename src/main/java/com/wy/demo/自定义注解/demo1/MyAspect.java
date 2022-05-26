package com.wy.demo.自定义注解.demo1;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect        // 开启切面功能
@Component    // 切面类需要加入IOC容器
public class MyAspect {
	// 先给出切点 也可以不事先给出 在后面直接用表达式指定切点
	// 切点就是标注了@AOPAnnotation注解的方法
    @Pointcut("@annotation(com.wy.demo.自定义注解.demo1.AOPAnnotation)")
    public void pointcut() {
    }

	// 后置通知
    @AfterReturning("pointcut()")
    public void doAfterReturn() {
        System.out.println("在标注了@AOPAnnotation的方法执行完成后执行... ...");
    }

	// 前置通知
    @Before("pointcut()")
    public void doBefore() {
        System.out.println("在标注了@AOPAnnotation的方法执行之前执行... ...");
    }
}
