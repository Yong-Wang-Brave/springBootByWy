package com.wy.demo.controller;

import com.wy.demo.controller.dto.Student;
import com.wy.demo.lightPoint.tokenGetUserInfo.HealthManageResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
@RestController
@Log4j2
public class CompletefutureController {

    @Autowired
    @Qualifier("asyncServiceExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @RequestMapping("/testThreadPoolExecutor")
    public HealthManageResult<List<Student>> test(){
        CompletableFuture<List<Student>> aCompletableFuture = CompletableFuture.supplyAsync(() -> test1(), threadPoolTaskExecutor);
        CompletableFuture<List<Student>> bCompletableFuture = CompletableFuture.supplyAsync(() -> test2(), threadPoolTaskExecutor);
        CompletableFuture<Void> future = CompletableFuture.allOf(aCompletableFuture, bCompletableFuture);
        future.join();

        List<Student> students = new ArrayList<>();

        try {
            students.addAll(aCompletableFuture.get());
            students.addAll(bCompletableFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  HealthManageResult.ok(students);
    }

    private List<Student>  test1() {
        List<Student> stu1 =new ArrayList<>();
        stu1.add(new Student("1","1","1","1",null,null));
        return  stu1;
    }
    private List<Student>  test2() {
        List<Student> stu2 =new ArrayList<>();
        stu2.add(new Student("2","2","2","2",null,null));
        return  stu2;
    }

}
