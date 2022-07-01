package com.wy.demo.spring.proxy;

import org.springframework.stereotype.Component;

@Component
public class UserDao implements IUserDao {

    @Override
    public String queryUserInfo() {
        return "实现类";
    }

}