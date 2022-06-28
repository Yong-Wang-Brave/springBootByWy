package com.wy.demo.controller;

import com.wy.demo.Redis.RedisUtils;
import com.wy.demo.lightspot.UnitedReturn.Result;
import com.wy.demo.lightspot.UnitedReturn.ResultCode;
import com.wy.demo.mybatis.entity.SortCourse;
import com.wy.demo.mybatis.mappers.SortCourseMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class RedisController {
    @Autowired
    private SortCourseMapper sortCourseMapper;

    @Autowired
    private RedisUtils redisLockUtil;


    @GetMapping("/1111")
    public Result getOrder() {
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

}
