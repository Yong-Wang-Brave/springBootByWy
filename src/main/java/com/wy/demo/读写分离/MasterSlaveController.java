package com.wy.demo.读写分离;


import com.wy.demo.mybatis.mappers.SortCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterSlaveController {
@Autowired
  private  SortCourseMapper sortCourseMapper;

    @RequestMapping("/master")
    public void master(){
        sortCourseMapper.findSortCourse();

    }

    @RequestMapping("/salve")
    public void slave(){
        sortCourseMapper.findSortCourse();
    }
}
