package com.wy.demo.thread.并发安全;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestThreadController0 {
    private int num = 0;

    @RequestMapping("/addNum0")
    public void addNum() {
        System.out.println(++num);
    }
}