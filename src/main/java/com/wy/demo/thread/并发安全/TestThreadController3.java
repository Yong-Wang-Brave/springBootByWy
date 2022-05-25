package com.wy.demo.thread.并发安全;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//这个方法 线程复用就会出问题
public class TestThreadController3 {
    private int num = 0;
    private final ThreadLocal <Integer> uniqueNum =
             new ThreadLocal <Integer> () {
                 @Override protected Integer initialValue() {
                     return num;
                 }
             };

    @RequestMapping("/addNum3")
    public void addNum() {
        int unum = uniqueNum.get();
       uniqueNum.set(++unum);
       System.out.println(uniqueNum.get()+"   "+Thread.currentThread().getName());

    }
}