package com.wy.demo.mybatis.generator.controller;

import com.wy.demo.mybatis.entity.SortCourse;
import com.wy.demo.mybatis.generator.dto.UserDTO;
import com.wy.demo.mybatis.generator.mapper.generator.UserDTOMapper;
import com.wy.demo.mybatis.mappers.SortCourseMapper;
import com.wy.demo.亮点.自定义注解实现统一返回.Result;
import com.wy.demo.亮点.自定义注解实现统一返回.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("/users")
public class MokeController {
    @Autowired
    UserDTOMapper    userDTOMapper;

    @GetMapping("/getUser")
    public Result getUser() {
        List<UserDTO> userDTOS = userDTOMapper.selectByExample(null);
        Result result = new Result(ResultCode.SUCESS, userDTOS);
        return result;
    }

}