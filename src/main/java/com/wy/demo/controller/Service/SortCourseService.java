package com.wy.demo.controller.Service;

import com.wy.demo.controller.dto.PageDto;
import com.wy.demo.controller.dto.PageResult;
import com.wy.demo.mybatis.entity.SortCourse;

public interface SortCourseService {
    PageResult<SortCourse> findSortCourseByDTO(PageDto pageDto);
}
