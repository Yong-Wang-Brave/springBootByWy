package com.wy.demo.controller.Service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wy.demo.Exception.Exception2.Result;
import com.wy.demo.Redis.redisLock.RedisLock;
import com.wy.demo.Tools.PageInfoUtils;
import com.wy.demo.controller.Service.SortCourseService;
import com.wy.demo.controller.dto.PageDto;
import com.wy.demo.controller.dto.PageResult;
import com.wy.demo.mybatis.entity.SortCourse;
import com.wy.demo.mybatis.mappers.SortCourseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
/**
 * springboot内部方法调用导致事务不起作用的原因与解决方案
 */
public class SortCourseServiceImpl implements SortCourseService {

    @Autowired
    SortCourseService sortCourseService;
    @Autowired
    SortCourseMapper sortCourseMapper;

    @Override
    @RedisLock(prefix = "aa",lockKey = "#pageDto.catId",waitSeconds = 5)
    public PageResult<SortCourse> findSortCourseByDTO(PageDto pageDto) {
        log.info("进来了");

        Page<SortCourse> page = PageHelper.startPage(pageDto.getPageNo(), pageDto.getPageSize()).doSelectPage(
                () -> {
                    sortCourseMapper.findSortCourseByDTO(pageDto);
                }
        );
        List<SortCourse> result = page.getResult();
        PageResult<SortCourse> pages = new PageResult<>(result, page.getPageNum(), page.getPageSize(), page.getTotal());



        return pages;
    }

    @Override
    public PageInfo<SortCourse> findSortCourseByDTOInfo(PageDto pageDto) {

        PageInfo<SortCourse> pageInfo =new PageInfo<>();
        List<SortCourse> sortCourseByDTO = sortCourseMapper.findSortCourseByDTO(pageDto);
        if (!CollectionUtils.isEmpty(sortCourseByDTO)) {
            pageInfo=PageInfoUtils.list2PageInfo(sortCourseByDTO,pageDto.getPageNo(),pageDto.getPageSize());
        }
        return pageInfo;
    }




    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insertTest(SortCourse sortCourse) {
        sortCourseMapper.insertTest(sortCourse);
       throw new RuntimeException("报错了");
    }

    @Override
    public Result queryTest(SortCourse course) {
this.sortCourseService.insertTest(course);
        return Result.ok();
    }




}

/*
方式一： 引入自身的bean
@AutoWired
private  SortCourseService sortCourseService;
this.sortCourseService.insertTest(course);

方式二：  通过applicationContext引入
@Autowired
ApplicationContext applicationContext;
 ((SortCourseServiceImpl)applicationContext.getBean("sortCourseServiceImpl")).insertTest(course);

 方式三： 通过AopContext获取当前类的代理类
 启动类添加  @EnableAspectJAutoProxy(exposeProxy = true)
 ((SortCourseService)AopContext.currentProxy()).insertTest(course);

 */
