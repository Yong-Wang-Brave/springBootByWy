package com.wy.demo.filter;

import com.wy.demo.jwt.UserService;
import com.wy.demo.mybatis.batch.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AppConfig {
    //@Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
}