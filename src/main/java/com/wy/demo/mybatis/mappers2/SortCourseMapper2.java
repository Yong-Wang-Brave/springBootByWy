package com.wy.demo.mybatis.mappers2;


import com.wy.demo.controller.dto.PageDto;
import com.wy.demo.mybatis.entity.SortCourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author huan.fu 2021/5/18 - 上午10:43
 */
@Mapper
public interface SortCourseMapper2 {

    //int addCustomer(@Param("phone") Encrypt phone, @Param("address") String address);

    List<SortCourse> findSortCourse();

    List<SortCourse> findSortCourseById(Long id);
    List<SortCourse> findSortCourseByDTO(PageDto pageDto);
}
