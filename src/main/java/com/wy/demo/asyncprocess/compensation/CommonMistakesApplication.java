package com.wy.demo.asyncprocess.compensation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
@EnableScheduling
public class CommonMistakesApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonMistakesApplication.class, args);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("aaa");
            }
        }).start();
    }
}

