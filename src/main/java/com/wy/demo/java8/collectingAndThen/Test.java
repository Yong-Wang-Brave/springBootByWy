package com.wy.demo.java8.collectingAndThen;

import com.wy.demo.controller.dto.Student2;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {

        List<Student2> students = Arrays.asList(new Student2("小张", 42, "", ""), new Student2("小王", 25, "", ""),
                new Student2("小李", 12, "", ""), new Student2("小赵", 32, "", ""));
              //无效的
        Map<Integer, Student2> collect = students.stream().sorted(Comparator.comparing(Student2::getAge)).collect(Collectors.toList()).stream().collect(Collectors.groupingBy(Student2::getAge, Collectors.collectingAndThen(Collectors.toList(), value -> value.get(0))));
        System.out.println(collect);
          //有效的List转map
        Map<Integer, List<Student2>> collect1 = students.stream().sorted(Comparator.comparing(Student2::getAge).reversed()).collect(Collectors.groupingBy(Student2::getAge,LinkedHashMap::new,Collectors.toList()));
        System.out.println(collect1);


    }
}
