package com.wy.demo.mybatis.mappers;

import com.wy.demo.controller.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserrMapper {
    User findByUserNameAndPassword(@Param("userName") String userName, @Param("passWord") String passWord);
}