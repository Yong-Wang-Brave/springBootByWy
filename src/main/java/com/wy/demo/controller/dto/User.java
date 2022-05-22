package com.wy.demo.controller.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class User {
 
    @Autowired
    private ApplicationContext applicationContext;
}
