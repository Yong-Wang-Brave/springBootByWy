package com.wy.demo.lightPoint.tokenGetUserInfo;


import com.alibaba.fastjson.JSON;
import com.wy.demo.Exception.Exception2.HealthManageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
@Slf4j
public class UserClient {

    //调用户中心接口获取用户信息
    public UserCenterUserInfo getUserInfo(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Accept-Charset","UTF-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        String result;
        try {
            log.info("调用用户中心获取信息，request header:{}", JSON.toJSONString(headers));
            result="调用户中心";
            log.info("调用用户中心获取信息，result:{}", result);
        } catch (Exception e) {
           throw new HealthManageException("调用用户中心获取信息异常",e);
        }
        UserCenterResult userCenterResult = JSON.parseObject(result, UserCenterResult.class);
        if (userCenterResult!=null && Objects.equals(200,userCenterResult.getCode())) {
            UserCenterUserInfo userInfo = userCenterResult.getData();
            if (userInfo==null) {throw new HealthManageException("调用用户中心返回异常");}
           return userInfo;
        }
throw  new HealthManageException(userCenterResult==null?"调用用户中心未获取任何消息":userCenterResult.getMsg());
    }
}
