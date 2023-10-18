package com.wy.demo.spring.kongzhibeanshunxu;

import com.wy.demo.mybatis.entity.SortCourse;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

@DependsOn(value = "applicationContextUtil")
//拦截器需要提前加载才行
public class SystemInfoService {

/*
    @Autowired
  private SortCourseMapper sortCourseMapper;
*/


    public static Map<String,List> cache = new HashMap<>();

    @PostConstruct
    public void init(){
       // List<SortCourse> sortCourse = sortCourseMapper.findSortCourse();
      List<SortCourse> sortCourse = new ArrayList<>();
        sortCourse.add(new SortCourse(1,"1",1,1,"1"));
        cache.put("sortCourseName",sortCourse);
        cache.put("sortCourseVersion",sortCourse);
    }
    public static List getKey(String keyName){
        return cache.get(keyName);
    };
}
