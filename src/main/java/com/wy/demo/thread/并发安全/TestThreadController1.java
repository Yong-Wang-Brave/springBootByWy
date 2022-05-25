package com.wy.demo.thread.并发安全;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope(value="prototype")
public class TestThreadController1 {
    private int num = 0;

    @RequestMapping("/addNum1")
    public void addNum() {
        System.out.println(++num);
    }
}