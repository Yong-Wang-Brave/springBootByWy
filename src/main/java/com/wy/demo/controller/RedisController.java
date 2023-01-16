package com.wy.demo.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
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
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@Log4j2
@Api(tags="我的课程")
public class RedisController {

    @Autowired
    private RedissonClient   redissonClient;

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
            boolean lock = redisLockUtil.lock("customerId", requestId, 10, TimeUnit.SECONDS);
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
            boolean lock = redisLockUtil.lock("customerId", requestId, 10,TimeUnit.SECONDS);
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


    @GetMapping("test/redis/lock")
    public void testRedisLock(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain;charset=utf-8");
        response.setHeader("Connection", "close");
        String value = IdUtil.fastSimpleUUID();
        try {
            if (redisLockUtil.lock("12345", value, 10L, TimeUnit.SECONDS)) {
                String s = String.format("%s 拿到锁了！！！！！！！！！！！", LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_MS_PATTERN));
                Thread.sleep(2000);
                response.getWriter().print(s);
                response.getWriter().flush();
                response.getWriter().close();
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            redisLockUtil.unlock("12345", value);
        }
        response.setStatus(301);
        response.getWriter().flush();
        response.getWriter().close();

    }

    @GetMapping("test/redission/lock")
    public void testRedissionLock(HttpServletResponse response,String lockKey) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain;charset=utf-8");
        response.setHeader("Connection", "close");
        RLock rLock = redissonClient.getLock(lockKey);
        RedissonRedLock redissonRedLock = new RedissonRedLock(rLock);
        try {
            //waitTime代表如果拿不到锁，最长等待时间
            //不需要设置过期时间，默认10s,任务执行时间过长会自动延长锁时间，宕机会释放锁，不会死锁
            if (redissonRedLock.tryLock(10L, TimeUnit.SECONDS)) {
                String s = String.format("%s 拿到锁了！！！！！！！！！！！", LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_MS_PATTERN));
                Thread.sleep(2000);
                response.getWriter().print(s);
                response.getWriter().flush();
                response.getWriter().close();
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            redissonRedLock.unlock();
        }
        response.setStatus(301);
        response.getWriter().flush();
        response.getWriter().close();

    }




}
