package com.wy.demo.springCloud.feign;

import com.wy.demo.Exception.Exception2.HealthManageException;
import com.wy.demo.Exception.Exception2.ServiceeException;
import com.wy.demo.controller.dto.Student;
import com.wy.demo.entity.User;
import com.wy.demo.lightPoint.tokenGetUserInfo.HealthManageResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class FeignClientFallBack implements FallbackFactory<FeignServiceWy> {
    private final static String FEIGN_ERROR_CODE="501";
    @Override
    public FeignServiceWy create(Throwable cause) {
        log.error("fallback reason:{}",cause.getMessage());
        return new FeignServiceWy(){
            @Override
            public User combineUser(String username) {
                throw new ServiceeException(FEIGN_ERROR_CODE,"invoke /getFeign failed");
            }

            @Override
            public HealthManageResult<Student> getSome(Student student) {
                throw new HealthManageException(FEIGN_ERROR_CODE,"invoke /getSome failed");
            }
        };
    }
}
