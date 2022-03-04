package com.wy.demo.Redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Slf4j
public class Redis1 implements Redis {
    Redis1(){
        System.out.println("你好1");
    }



}

