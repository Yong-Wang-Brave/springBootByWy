package com.wy.demo.mybatis.generator.controller;

import com.wy.demo.mybatis.entity.SortCourse;
import com.wy.demo.mybatis.generator.dto.QueryUserDTO;
import com.wy.demo.mybatis.generator.dto.UserDTO;
import com.wy.demo.mybatis.generator.mapper.generator.UserDTOMapper;
import com.wy.demo.mybatis.mappers.SortCourseMapper;
import com.wy.demo.亮点.自定义注解实现统一返回.Result;
import com.wy.demo.亮点.自定义注解实现统一返回.Result2;
import com.wy.demo.亮点.自定义注解实现统一返回.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "用户表")
@RestController
@RequestMapping("/users")
public class MokeController {
    @Autowired
    UserDTOMapper    userDTOMapper;

    @PostMapping("/getUser")
    @ApiOperation("用户表-获取用户信息")
    public Result2<UserDTO> getUser(@RequestBody QueryUserDTO queryUserDTO) {
        UserDTO userDTO = userDTOMapper.selectByPrimaryKey(queryUserDTO.getId());
        return  Result2.sucess(userDTO);
    }

}