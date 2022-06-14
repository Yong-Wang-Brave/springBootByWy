package com.wy.demo.controller;


import com.github.pagehelper.PageInfo;
import com.wy.demo.controller.Service.SortCourseService;
import com.wy.demo.controller.dto.PageDto;
import com.wy.demo.controller.dto.PageResult;
import com.wy.demo.lightspot.UnitedReturn.Result2;
import com.wy.demo.mybatis.entity.SortCourse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/api/pageHelper")
@Api(tags="分页配置")
public class pageHelperController {

    @Autowired
    SortCourseService sortCourseService;

     @PostMapping(value="/queryPage")
     @ApiOperation("获取课程列表-分页")
    public Result2<PageResult<SortCourse>> getAllCourse(@RequestBody PageDto pageDto){
        PageResult<SortCourse> list = sortCourseService.findSortCourseByDTO(pageDto);
        return Result2.sucess(list);
    }

    @PostMapping(value="/queryPageInfo")
    @ApiOperation("获取课程列表-分页")
    public Result2<PageInfo<SortCourse>> getAllCoursePageInfo(@RequestBody PageDto pageDto){
        PageInfo<SortCourse> list = sortCourseService.findSortCourseByDTOInfo(pageDto);
        return Result2.sucess(list);
    }

}
