package com.wy.demo.mybatis.batch;

import com.wy.demo.entity.UserBatch;
import com.wy.demo.jwt.UserService;
import com.wy.demo.mybatis.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(String userName, String passWord) throws Exception {
        return null;
    }

    @Override
    public void batchInsert(List<UserBatch> userList) {
        userMapper.batchInsert(userList);
    }
}
