package com.wy.demo.shejimoshi.策略模式22;

import com.wy.demo.Exception.Exception2.Result;
import com.wy.demo.spring.DTO.InParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class TestCelueController {

    @Autowired
    TypeFactory typeCelue;
    @Autowired
    SpringContextHelper2 type2;

    @PostMapping("/findByType")
    Result testCelue(@RequestBody(required = false) InParam in) {
        Object bean = type2.getBean("bikeImpl");
        return Result.ok(typeCelue.getType(in.getType()).getTypeList(in));
    }
}
