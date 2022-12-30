package com.wy.demo.controller;

import com.wy.demo.Redis.RedisUtils;
import com.wy.demo.entity.User;
import com.wy.demo.lightspot.UnitedReturn.Result;
import com.wy.demo.lightspot.UnitedReturn.ResultCode;
import com.wy.demo.mybatis.entity.SortCourse;
import com.wy.demo.mybatis.mappers.SortCourseMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@Api(tags="我的课程")
public class RedisController {
    @Autowired
    private SortCourseMapper sortCourseMapper;

    @Autowired
    private RedisUtils redisLockUtil;

    @ApiOperation("查询课程(参考模板)")
    @GetMapping("/findCourse")
    public Result getOrder(@RequestBody(required = false) User user) {
        Result result = null;
        String requestId= UUID.randomUUID().toString();
        try {
            boolean lock = redisLockUtil.lock("customerId", requestId, 10);
            List<SortCourse> sortCourse;
            if (lock) {
                sortCourse = sortCourseMapper.findSortCourse();
                result = new Result(ResultCode.SUCESS, sortCourse);
                return result;
            } else {
                return  new Result(ResultCode.ERROR,null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisLockUtil.unlock("customerId", requestId);
        }
        return result;
    }



    @GetMapping("/findCourseById")
    public Result getOrder1(@Param("id") String id) {
        Result result = null;
        String requestId= UUID.randomUUID().toString();
        try {
            boolean lock = redisLockUtil.lock("customerId", requestId, 10);
            List<SortCourse> sortCourse;
            if (lock) {
                sortCourse = sortCourseMapper.findSortCourseById(Long.parseLong(id));
                result = new Result(ResultCode.SUCESS, sortCourse);
                return result;
            } else {
                return  new Result(ResultCode.ERROR,null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisLockUtil.unlock("customerId", requestId);
        }
        return result;
    }
}
