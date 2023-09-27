package com.wy.demo.DistributedLock2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController3 {

    @DistributedLock("myLock2")
    @GetMapping("/myEndpoint2")
    public String myEndpoint() {
        // 执行需要加锁的业务逻辑
        // ...
        return "Success";
    }
}