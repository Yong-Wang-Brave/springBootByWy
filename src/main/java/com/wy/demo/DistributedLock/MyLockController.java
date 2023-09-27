package com.wy.demo.DistributedLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyLockController {

    @Autowired
    private DistributedLock distributedLock;

    @GetMapping("/myEndpoint")
    public String myEndpoint() {
        boolean lockAcquired = distributedLock.acquireLock();
        if (lockAcquired) {
            try {
                // 执行需要加锁的业务逻辑
                // ...

                return "Success";
            } finally {
                //这一块就可以让第二次操作获取不到锁
               distributedLock.releaseLock();
            }
        } else {
            return "Failed to acquire lock";
        }
    }
}