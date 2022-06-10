package com.wy.demo.controller.Service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wy.demo.controller.Service.SortCourseService;
import com.wy.demo.controller.dto.PageDto;
import com.wy.demo.controller.dto.PageResult;
import com.wy.demo.mybatis.entity.SortCourse;
import com.wy.demo.mybatis.mappers.SortCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SortCourseServiceImpl implements SortCourseService {
    @Autowired
    SortCourseMapper sortCourseMapper;
    @Override
    public PageResult<SortCourse> findSortCourseByDTO(PageDto pageDto) {

        Page<SortCourse> page = PageHelper.startPage(pageDto.getPageNo(), pageDto.getPageSize()).doSelectPage(
                () -> {
                    sortCourseMapper.findSortCourseByDTO(pageDto);
                }
        );
        List<SortCourse> result = page.getResult();
        PageResult<SortCourse> pages = new PageResult<>(result, page.getPageNum(), page.getPageSize(), page.getTotal());



        return pages;
    }
}
