package com.wy.demo.机器人;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
//lambda表达式筛选出集合中是数字的元素
        List<String> list = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        //list集合转换成map
        Map<String, String> map = list.stream().collect(Collectors.toMap(Function.identity(), e -> e));
        System.out.println(map);
        //map的demo
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

        objectObjectHashMap.put("1", "1");
        objectObjectHashMap.put("2", "2");
        objectObjectHashMap.put("3", "3");
        //map转json

        String json = JSON.toJSONString(objectObjectHashMap);
        System.out.println(json);


        // alt + [  ]   选择上一个 下一个  alt +q 给出提示    tab键确认
            }










}