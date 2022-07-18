package com.wy.demo.mybatiesInterceptor3.controller;


import com.wy.demo.mybatis.entity.SortCourse;
import com.wy.demo.mybatis.mappers.SortCourseMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Think
 * @Title: welocome
 * @ProjectName token-authentication
 * @Description: TODO
 * @date 2019/1/1815:4112211
 */
@RestController
@Log4j2
public class lanjie {

    @Autowired
    SortCourseMapper sortCourseMapper;


   // @GetMapping("/getAllCourse")
    public   List<SortCourse> getAll(){
        List<SortCourse> courses = sortCourseMapper.findSortCourse();
    return courses;
    };



}
