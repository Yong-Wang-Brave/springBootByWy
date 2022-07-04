package com.wy.demo.java8.Predicate.非空校验;

import org.springframework.util.StringUtils;

import java.util.function.Predicate;

public class TestMain {

    public static void main(String[] args) {
        Predicate<String> predicate = s -> StringUtils.isEmpty(s);
        String   b=null;
        if (predicate.test(b)) {
            System.out.println("空的");
        }else{
            System.out.println("非空");
        }

    }
}
