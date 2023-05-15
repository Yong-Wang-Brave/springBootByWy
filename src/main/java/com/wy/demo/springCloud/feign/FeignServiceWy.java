package com.wy.demo.springCloud.feign;


import com.wy.demo.controller.dto.Student;
import com.wy.demo.entity.User;
import com.wy.demo.lightPoint.tokenGetUserInfo.HealthManageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "springBoot-8082",path="/wy",url="${parse-pdf-domainName}",fallbackFactory =FeignClientFallBack.class,configuration = FeignConfiguration.class)
public interface FeignServiceWy {
  @GetMapping("/getFeign")
  User combineUser(String username);

  @PostMapping("/get/someStudent")
  HealthManageResult<Student> getSome();

}
