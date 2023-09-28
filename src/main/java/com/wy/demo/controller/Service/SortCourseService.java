package com.wy.demo.controller.Service;

import com.github.pagehelper.PageInfo;
import com.wy.demo.Exception.Exception2.Result;
import com.wy.demo.controller.dto.PageDto;
import com.wy.demo.controller.dto.PageResult;
import com.wy.demo.mybatis.entity.SortCourse;

public interface SortCourseService {
    PageResult<SortCourse> findSortCourseByDTO(PageDto pageDto);

    PageInfo<SortCourse> findSortCourseByDTOInfo(PageDto pageDto);

    void  insertTest(SortCourse  sortCourse);

   Result queryTest(SortCourse course);
}
