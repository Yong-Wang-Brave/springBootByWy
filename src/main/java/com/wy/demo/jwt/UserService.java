package com.wy.demo.jwt;

import com.wy.demo.entity.UserBatch;

import java.util.List;

public interface UserService {
    String login(String userName, String passWord) throws Exception;

   void batchInsert(List<UserBatch> userList);
}
