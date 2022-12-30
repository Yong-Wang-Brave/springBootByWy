package com.wy.demo.controller;

import com.wy.demo.bejson_gen_beans.cn.json.pojo.Children;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.JsonRootBean;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.Message;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.Meta;
import com.wy.demo.mybatis.druidMapper.DruidMapper;
import com.wy.demo.mybatis.entity.SortCourse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@RestController
@Log4j2
public class DruidController {
    @Autowired
    DruidMapper sortCourseMapper;
    @GetMapping("/getDruid")
    public JsonRootBean getAll(){
        List<SortCourse> courses = sortCourseMapper.findSortCourse();
        List<Message> messages =new ArrayList<>();
        //赛选出0层的放入messages
        List<SortCourse> collect = courses.stream().filter(o -> o.getCatLevel()==0).collect(Collectors.toList());
        for (SortCourse sortCourse : collect) {
            Message build = Message.builder().cat_id(sortCourse.getCatId()).cat_name(sortCourse.getCatName()).cat_pid(sortCourse.getCatPid())
                    .cat_level(sortCourse.getCatLevel()).cat_deleted((Boolean.valueOf(sortCourse.getCatDeleted()))).build();
            messages.add(build);
        }
        //遍历0层，赛选出0层对应的1层的放入messages
        for (Message message : messages) {
            List<Children> children1 =new ArrayList<>();
            List<SortCourse> collect1 = courses.stream().filter(o -> o.getCatLevel()==1).filter(o->o.getCatPid()==message.getCat_id()).collect(Collectors.toList());
            for (SortCourse sortCourse : collect1) {
                Children build = Children.builder().cat_id(sortCourse.getCatId()).cat_name(sortCourse.getCatName()).cat_pid(sortCourse.getCatPid())
                        .cat_level(sortCourse.getCatLevel()).cat_deleted(Boolean.valueOf(sortCourse.getCatDeleted())).build();
                children1.add(build);
            }
            message.setChildren(children1);
        }
        //遍历1层，刷选出符合条件的二层
        for (Message message : messages) {
            for (Children child : message.getChildren()) {

                List<SortCourse> collect1 = courses.stream().filter(o -> o.getCatLevel()==2).filter(o->o.getCatPid()==child.getCat_id()).collect(Collectors.toList());
                List<Children> children1 =new ArrayList<>();
                for (SortCourse sortCourse : collect1) {
                    Children build = Children.builder().cat_id(sortCourse.getCatId()).cat_name(sortCourse.getCatName()).cat_pid(sortCourse.getCatPid())
                            .cat_level(sortCourse.getCatLevel()).cat_deleted(Boolean.valueOf(sortCourse.getCatDeleted())).build();
                    children1.add(build);
                }
                child.setChildren(children1);
            }

        }

        JsonRootBean jsonRootBean =new JsonRootBean();
        jsonRootBean.setMessage(messages);
        Meta meta =new Meta();
        meta.setMsg("sucess");
        meta.setStatus(200);
        jsonRootBean.setMeta(meta);
        return jsonRootBean;
    };
}
