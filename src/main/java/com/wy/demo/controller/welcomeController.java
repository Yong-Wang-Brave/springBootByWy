package com.wy.demo.controller;


import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.wy.demo.Exception.Exception2.Result;
import com.wy.demo.Exception.Exception2.ServiceeException;
import com.wy.demo.SpringContext.SpringContextHolder;
import com.wy.demo.SpringContext.SpringUtils;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.Children;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.JsonRootBean;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.Message;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.Meta;
import com.wy.demo.controller.dto.Student;
import com.wy.demo.controller.dto.User;
import com.wy.demo.entity.UserReq;
import com.wy.demo.mybatis.entity.SortCourse;
import com.wy.demo.mybatis.mappers.SortCourseMapper;
import com.wy.demo.mybatis.mappers.UserMapper;
import com.wy.demo.springCloud.feign.FeignServiceWy;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.rmi.server.ServerCloneException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Think
 * @Title: welocome
 * @ProjectName token-authentication
 * @Description: TODO
 * @date 2019/1/1815:4112211
 */
@RestController
@Log4j2
public class welcomeController {



    @Autowired
    UserMapper userMapper;
    @Autowired
    FeignServiceWy feignServiceWy;
    @Autowired
    SortCourseMapper sortCourseMapper;
    @Autowired
    com.wy.demo.controller.Service.SortCourseService sortCourseService;

    @PostMapping("/update")
    public String welcome(@RequestBody UserReq userReq,@SessionAttribute(value = "wy",required = false)String wy) {
           log.info(wy);
        userMapper.updateUser(userReq);

        return "welcome token authentication";
    }

    @PostMapping("/update2")
    public static String welcome(HttpServletRequest request,@SessionAttribute(value = "applicationContext",required = false)ApplicationContext applicationContext) {
        log.info(request.getHeader("wy"));

        User bean = SpringUtils.getBean(User.class);

        User stu = SpringContextHolder.getBean(User.class);

        return "welcome token authentication";
    }

    @GetMapping("/stat")
    public Object druidStat(){
        // ??????????????????????????????
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
    @GetMapping("/get")
    public String get() {
        log.info("get");
        return "get";
    }

    @GetMapping("/online2")
    public String online() throws Exception {
        log.info("online");
       // return "online";
     //   throw new ServiceeException("nimei");
        throw  new Exception("NIMEI ");
    }
    @GetMapping("/putUsername")
    public User getUser(String username) throws ServerCloneException {
       // User user = feignServiceWy.combineUser(username);
        User user =null;
        throw  new ServiceeException("aa");

    };


    @GetMapping("/getAllCourse")
    public   JsonRootBean getAll(){
        List<SortCourse> courses = sortCourseMapper.findSortCourse();
        List<Message> messages =new ArrayList<>();
        //?????????0????????????messages
        List<SortCourse> collect = courses.stream().filter(o -> o.getCatLevel()==0).collect(Collectors.toList());
        for (SortCourse sortCourse : collect) {
            Message build = Message.builder().cat_id(sortCourse.getCatId()).cat_name(sortCourse.getCatName()).cat_pid(sortCourse.getCatPid())
                    .cat_level(sortCourse.getCatLevel()).cat_deleted((Boolean.valueOf(sortCourse.getCatDeleted()))).build();
            messages.add(build);
        }
        //??????0???????????????0????????????1????????????messages
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
        //??????1????????????????????????????????????
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

@RequestMapping("/testException")
   public Student  testBindException(@Valid @RequestBody Student student){
return student;
    }

    @PostMapping("/to_list")
    /**
     * ??????????????????
     */
    public Result list(com.wy.demo.entity.User user) {
        String username = user.getUsername();
        //????????????

        return Result.ok(username);
    }


}
