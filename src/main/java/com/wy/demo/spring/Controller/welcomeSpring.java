package com.wy.demo.spring.Controller;


import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.wy.demo.Exception.Exception2.Result;
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
import com.wy.demo.spring.DTO.InParam;
import com.wy.demo.spring.TypeFactory;
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
public class welcomeSpring {

@Autowired
  private TypeFactory typeFactory;


    /**
     * 这是一种设计模式，代码看着更优雅
     * @param param
     * @return
     */
    @PostMapping("/getFactory")
    public Result get(@RequestBody InParam param) {

        return typeFactory.getType(param.getType()).getTypeList(param);
    }



}
