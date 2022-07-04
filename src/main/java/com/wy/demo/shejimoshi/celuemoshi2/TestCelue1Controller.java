package com.wy.demo.shejimoshi.celuemoshi2;

import com.wy.demo.Exception.Exception2.Result;
import com.wy.demo.spring.DTO.InParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class TestCelue1Controller {

    @Autowired
    PayContext payContext;

    @PostMapping("/findByType1")
    Result testCelue(@RequestBody(required = false) InParam in) {
        return Result.ok(payContext.pay(in.getType()).pay(in));
    }
}
