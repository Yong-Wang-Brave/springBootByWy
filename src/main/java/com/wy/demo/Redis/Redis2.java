package com.wy.demo.Redis;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
public class Redis2 implements Redis {
    Redis2(){
        System.out.println("你好2");
    }
}
