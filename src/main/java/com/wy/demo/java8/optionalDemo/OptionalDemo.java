package com.wy.demo.java8.optionalDemo;

import com.wy.demo.mybatis.entity.SortCourse;
import com.wy.demo.机器人.Test;

import java.util.*;

public class OptionalDemo {
    public static void main(String[] args) {
        SortCourse test = new SortCourse();
 test.setCatName("你好");
        SortCourse test1 = Optional.ofNullable(test).orElse(new SortCourse());
        System.out.println(test1);

        // 创建一个 HashMap
        Map<String, String> map = new HashMap<>();

        // 向 map 中添加一些数据
        map.computeIfAbsent("key1", k -> "value1");
        map.computeIfAbsent("key2", k -> "value2");
        map.computeIfAbsent("key1", k -> "value3");

        // 输出 map 的内容
        System.out.println(map);




    }
}
