package com.wy.demo.controller;


import com.wy.demo.Exception.Exception2.ServiceeException;
import com.wy.demo.entity.User;
import com.wy.demo.entity.UserReq;
import com.wy.demo.mapper.UserMapper;
import com.wy.demo.springCloud.feign.FeignServiceWy;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Think
 * @Title: welocome
 * @ProjectName token-authentication
 * @Description: TODO
 * @date 2019/1/1815:4112211
 */
@RestController
@Log4j2
public class welcome {

    Logger logger = LoggerFactory.getLogger(com.wy.demo.controller.welcome.class);


    @Autowired
    UserMapper userMapper;
    @Autowired
    FeignServiceWy feignServiceWy;

    @PostMapping("/update")
    public String welcome(@RequestBody UserReq userReq,@SessionAttribute(value = "wy",required = false)String wy) {
           log.info(wy);
        userMapper.updateUser(userReq);

        return "welcome token authentication";
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
    public User getUser(String username){
        User user = feignServiceWy.combineUser(username);

       return user;
    };
}
