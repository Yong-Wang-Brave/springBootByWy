package com.wy.demo.spring.Restemplate;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Log4j2
public class TestRestTemplateController {
    @Resource
    RestClient restClient;
    @GetMapping("/getAllCourseByRestTemplate")
    public String getAll(){

        String s = restClient.get("https://www.brave-wang.top:8081/getAllCourse");
        System.out.println(s);

return s;
    }
}
