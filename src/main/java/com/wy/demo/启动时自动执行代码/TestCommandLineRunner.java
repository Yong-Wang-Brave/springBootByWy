package com.wy.demo.启动时自动执行代码;

import com.wy.demo.controller.JSONController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class TestCommandLineRunner implements CommandLineRunner {
@Autowired
    JSONController JSONController;
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("order2:TestCommandLineRunner");
       // JSONController.LoadFromProperties();
    }
}