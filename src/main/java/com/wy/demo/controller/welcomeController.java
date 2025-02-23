package com.wy.demo.controller;


import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.alibaba.fastjson.JSONObject;
import com.wy.demo.Exception.Exception2.HealthManageException;
import com.wy.demo.Exception.Exception2.Result;
import com.wy.demo.Exception.Exception2.ServiceeException;
import com.wy.demo.SpringContext.SpringContextHolder;
import com.wy.demo.SpringContext.SpringUtils;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.Children;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.JsonRootBean;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.Message;
import com.wy.demo.bejson_gen_beans.cn.json.pojo.Meta;
import com.wy.demo.config.CacheModelDefiend;
import com.wy.demo.controller.Service.SortCourseService;
import com.wy.demo.controller.dto.Student;
import com.wy.demo.controller.dto.User;
import com.wy.demo.entity.UserReq;
import com.wy.demo.lightPoint.tokenGetUserInfo.HealthManageResult;
import com.wy.demo.mybatis.entity.SortCourse;
import com.wy.demo.mybatis.mappers.SortCourseMapper;
import com.wy.demo.mybatis.mappers.UserMapper;
import com.wy.demo.mybatis.mappers2.SortCourseMapper2;
import com.wy.demo.springCloud.feign.FeignServiceWy;
import com.wy.demo.xielaoshi.xianchengchigongjulei.ThreadPoolExecuteTypeEnum;
import com.wy.demo.xielaoshi.xianchengchigongjulei.ThreadPoolService;
import com.wy.demo.zhidingshujuyuan.DynamicDataSourceSwitch;
import com.wy.demo.zhidingshujuyuan.MerchantTransactional;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.rmi.server.ServerCloneException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
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
SortCourseService sortCourseService;


    @Autowired
    UserMapper userMapper;
    @Autowired
    FeignServiceWy feignServiceWy;
    @Autowired
    SortCourseMapper sortCourseMapper;
    @Autowired
    SortCourseMapper2 sortCourseMapper2;


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
        // 获取数据源的监控数据
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
    @GetMapping("/get")
   //ss @Cacheable(value=CacheModelDefiend.KEY_HOUR_HALF)
    public JSONObject get() throws InterruptedException {
        log.info("get");
        Map<String,Object> result =new ConcurrentHashMap<>();
        List<String> errorList = Collections.synchronizedList(new ArrayList<>());
        ThreadPoolService.execute(ThreadPoolExecuteTypeEnum.ASY,getTask1(result,errorList));
        Thread.sleep(3000);
        return buildResult1(result);
    }

    @GetMapping("/get1")
    @Cacheable(value = CacheModelDefiend.KEY_HOUR_HALF)
    public String get1()  {
        log.info("get1");

        return CacheModelDefiend.KEY_HOUR_HALF;
    }

    private JSONObject buildResult1(Map<String, Object> result) {
        JSONObject rtn = new JSONObject();
        rtn.put("AA",result.get("A"));
        rtn.put("BB",result.get("B"));
        rtn.put("CC",result.get("C"));
        return rtn;
    }

    private List<Runnable> getTask1(Map<String, Object> result, List<String> errorList) {
       List<Runnable> runnable = new ArrayList<>();
       runnable.add(getTask2("A",result,errorList,()->{
         return Arrays.asList("A");
       }));
        runnable.add(getTask2("B",result,errorList,()->{
            return Arrays.asList("B");
        }));
        runnable.add(getTask2("C",result,errorList,()->{
            return Arrays.asList("C");
        }));
        return  runnable;
    }

    private <T> Runnable getTask2(String name, Map<String, Object> map, List<String> errorList,GetData getData) {
        return () -> {
            try {
                map.put(name,getData.get());
            } catch (Exception e) {
                errorList.add(name);
            }
        };
    }

    @GetMapping("/online")
    public String online1() throws Exception {
        log.info("online");
        return "online";   }
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
    @GetMapping("/getHealthManage")
    public HealthManageResult getHealthManage()  {
        // User user = feignServiceWy.combineUser(username);
        User user =null;
        throw  new HealthManageException("aa");

    };
    @PostMapping ("/get/someStudent")
    public HealthManageResult postHealthManage()  {
      return   feignServiceWy.getSome();

      //return HealthManageResult.ok(UserContext.getUserInfo().getUserId());

    };

    @DynamicDataSourceSwitch("aa")
    @MerchantTransactional
    @CrossOrigin
    @GetMapping("/getAllCourse")
    public   JsonRootBean getAll(){
        String activeProfile = SpringUtil.getActiveProfile();
        System.out.println(activeProfile);
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


    @DynamicDataSourceSwitch("aa")
    @MerchantTransactional
    @GetMapping("/getAllCourse2")
    public   JsonRootBean getAll2(){
        String activeProfile = SpringUtil.getActiveProfile();
        System.out.println(activeProfile);
        List<SortCourse> courses = sortCourseMapper2.findSortCourse();
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

@RequestMapping("/testException")
   public Student  testBindException(@Valid @RequestBody Student student){
return student;
    }

    @PostMapping("/to_list")
    /**
     * 改进后的写法
     */
    public Result list(com.wy.demo.entity.User user) {
        String username = user.getUsername();
        //业务逻辑

        return Result.ok(username);
    }




    @GetMapping("/add/1")
    public HealthManageResult add()  {
        // User user = feignServiceWy.combineUser(username);
        User user =null;
     return HealthManageResult.ok();

    };

    @GetMapping("/get/mdc")
    public HealthManageResult getMdc()  {
        // User user = feignServiceWy.combineUser(username);
        String userInfo = MDC.get("userInfo");
        User user =null;
        return HealthManageResult.ok(userInfo);

    };

    @GetMapping("/test/transactionInvalid")
    public HealthManageResult transactionInvalid()  {
        SortCourse sortCourse = new SortCourse();
        sortCourse.setCatId(11111111);
        sortCourse.setCatName("111");
        sortCourseService.queryTest(sortCourse);
        return HealthManageResult.ok();

    };



}
