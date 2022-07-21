package com.wy.demo.java8.lambda.flatmap;

import com.wy.demo.entity.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Project：CodeFragment
 * FullPath：cn.itoak.FlatMapUsage
 * Date：2019/9/20
 * Time：16:15
 * Description：flatMap方法用法
 *
 * @author itoak
 * @version 1.0
 */
public class FlatMapUsage {

    public static void handle(List<String> list) {
         Optional.ofNullable(list)//判空
                .orElse(new ArrayList<>())//空处理
                .stream()//转流操作
                .map(str -> str.split(","))//将列表单个元素分离，得到数组
                .flatMap(Arrays::stream)//将上一步得到的数组转流，并拼接成一个stream
                .collect(Collectors.toSet())
                .forEach(System.out::println);//将流转Set集合，去重
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a,b,c", "b,c,e", "a,d,f", "a,e", "b,d");
        handle(list);








    }
}

