package com.wy.demo.mybatiesInterceptor3.controller;


import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.Children;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.JsonRootBean;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.Message;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.Meta;
import com.wy.demo.controller.dto.Student;
import com.wy.demo.entity.User;
import com.wy.demo.entity.UserReq;
import com.wy.demo.mapper.UserMapper;
import com.wy.demo.mybatis.entity.SortCourse;
import com.wy.demo.mybatis.mappers.SortCourseMapper;
import com.wy.demo.springCloud.feign.FeignServiceWy;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
