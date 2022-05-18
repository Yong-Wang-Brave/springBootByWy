package com.wy.demo.lightspot.UnitedReturn.controller;

import com.wy.demo.mybatis.entity.SortCourse;
import com.wy.demo.mybatis.mappers.SortCourseMapper;
import com.wy.demo.lightspot.UnitedReturn.ResponseResult;
import com.wy.demo.lightspot.UnitedReturn.Result;
import com.wy.demo.lightspot.UnitedReturn.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    SortCourseMapper sortCourseMapper;

    @GetMapping("/1")
    public Result getOrder() {
        List<SortCourse> sortCourse = sortCourseMapper.findSortCourse();
        Result result = new Result(ResultCode.SUCESS, sortCourse);
        return result;
    }
//优化2
    @GetMapping("/2")
    public Result getOrder2() {
        List<SortCourse> sortCourse = sortCourseMapper.findSortCourse();
        if (1 == 2) {
            return Result.failure(ResultCode.SUCESS);
        }
        return Result.sucess(sortCourse);
    }
    //优化3 Result封装对象没有业务含义
    // sucess failure 是不是很多余
    //思路：定义一个自定义注解，其实就是一个标记 有标记返回result 标记不返回
    @GetMapping("/3")
    @ResponseResult
    public List<SortCourse> getSortCourse(){
        List<SortCourse> sortCourse = sortCourseMapper.findSortCourse();
        return sortCourse;

    }


}