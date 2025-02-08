package com.wy.demo.自定义注解.castAsString;

import com.alibaba.fastjson.JSON;
import com.wy.demo.dto.Student;
import com.wy.demo.dto.Student3;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class MymyController {

    @GetMapping("/data")
   // @CastAsString(paths = {"$.age", "$.user.id"}) // 将 JSON 中的 age 和 user.id 字段转换为字符串
    @CastAsString(paths = {"$.number", "$.student3List.[*].number1", "$.student3List.[*].number2"}) // 将 JSON 中的 age 和 user.id 字段转换为字符串
    public String getData() {
        // 返回一个 JSON 字符串
        Student student = new Student();
        //会展示科学计数法
       // student.setNumber(new BigDecimal("0.0000012345"));
        student.setNumber(new BigDecimal("0.0000012345"));
        Student3 student3 = new Student3();
        student3.setNumber1(new BigDecimal("788.0445"));
        student3.setNumber2(new BigDecimal("9900.0445"));
        student.setStudent3List(Arrays.asList(student3));
        return JSON.toJSONString(student);
        //return "{\"name\":\"John\", \"age\":30000100000000000400000005000000, \"user\":{\"id\":123, \"name\":\"Alice\"}}";
    }
}