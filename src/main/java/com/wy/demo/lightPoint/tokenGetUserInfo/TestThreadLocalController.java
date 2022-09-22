package com.wy.demo.lightPoint.tokenGetUserInfo;


import com.wy.demo.lightPoint.tokenGetUserInfo.threadLocal.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/runHealth/healthManage")
public class TestThreadLocalController {

    @GetMapping("getByAnnocation")
  public HealthManageResult  getByAnnocation(@RequestAttribute(value = "userId",required = false) String userId ){

    log.info("当前登录的用户{}",userId);
        return HealthManageResult.ok("成功");

    }
    @GetMapping("getByThreadLocal")
    public HealthManageResult  getByThreadLocal(){

        log.info("当前登录的用户{}", UserContext.getUserInfo().getUserId());
        return HealthManageResult.ok("成功");

    }

}
