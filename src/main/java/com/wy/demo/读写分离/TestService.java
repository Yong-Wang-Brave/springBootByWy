package com.wy.demo.读写分离;



import com.wy.demo.mybatis.entity.SortCourse;
import com.wy.demo.mybatis.mappers.SortCourseMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ys 2021/4/8
 * @description
 */
@Service
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class TestService {

  @Autowired
  SortCourseMapper sortCourseMapper;


  @TargetDataSource("master")
  public List<SortCourse> getMater(){
    List<SortCourse> sortCourse = sortCourseMapper.findSortCourse();
    return sortCourse;
  }
  @TargetDataSource("slave")
  public List<SortCourse> getSlave(){
    List<SortCourse> sortCourse = sortCourseMapper.findSortCourse();
    return sortCourse;
  }
}
