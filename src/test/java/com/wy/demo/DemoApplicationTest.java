package com.wy.demo;


import com.wy.demo.controller.welcomeController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTest {

    @Autowired
    welcomeController welcomeController;

    @Test
    public void contextLoads(){
        log.info("========开始========");
       // welcomeController.get();
        log.info("========结束========");

    }






}
