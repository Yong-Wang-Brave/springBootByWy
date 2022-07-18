package com.wy.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
 
    @Autowired
    private ApplicationContext applicationContext;
    int i;
    String userName;
    String passWord;
    public User(int i, String toString) {
    }
}
