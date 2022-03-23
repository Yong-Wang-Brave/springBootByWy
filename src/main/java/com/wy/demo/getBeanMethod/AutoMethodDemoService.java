package com.wy.demo.getBeanMethod;


import org.springframework.stereotype.Service;

@Service
public class AutoMethodDemoService {
    public String test() {
        return "test1";
    }

    public String test2() {
        return "test2";
    }

    public String test3() {

        return "test3";
    }
}
