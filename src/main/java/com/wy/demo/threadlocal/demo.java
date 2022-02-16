package com.wy.demo.threadlocal;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
@Log4j2
/**
 * ，1）程序运
 * 行在 Tomcat 中，执行程序的线程是 Tomcat 的工作线程，而 Tomcat 的工作线程是基于
 * 线程池的。
 * 2）线程池会重用，
 * 3）ThreadLocal 适用于变量在线程间隔离，而在方法或类间共享的场景。
 * 所以每个线程使用之后，都要清除
 */
public class demo {
    private ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);
    @GetMapping("wrong")
    public Map wrong(@RequestParam("userId") Integer userId) {
        Map result;
        try {
//设置用户信息之前先查询一次ThreadLocal中的用户信息
            String before = Thread.currentThread().getName() + ":" + currentUser.get();
//设置用户信息到ThreadLocal
            currentUser.set(userId);
//设置用户信息之后再查询一次ThreadLocal中的用户信息;
            String after = Thread.currentThread().getName() + ":" + currentUser.get();
//汇总输出两次查询结果
            result = new HashMap();
            result.put("before", before);
            result.put("after", after);
        } finally {
            currentUser.remove();
        }
        return result;
    }
}
