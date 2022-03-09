package com.wy.demo.springCloud.feign;


import com.wy.demo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "springBoot-8002",fallbackFactory =FeignClientFallBack.class,configuration = FeignConfiguration.class)
public interface FeignServiceWy {
  @GetMapping("/getFeign")
  User combineUser(String username);

}
