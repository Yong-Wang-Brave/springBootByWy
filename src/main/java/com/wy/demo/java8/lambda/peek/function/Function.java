package com.wy.demo.java8.lambda.peek.function;

import com.wy.demo.java8.lambda.peek.DTO.Student1;
import com.wy.demo.java8.lambda.peek.DTO.Student2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 赋值
 */
public class Function {

    public static void main(String[] args) {
        List<Student1> student1s = Arrays.asList(new Student1("name1", "age1", "sex1"), new Student1("name11", "age11", "sex11"));

       List<Student2> stu2=student1s.stream().map(changeToStu2()).collect(Collectors.toList());
        System.out.println(stu2);
    }
//自动传入stu1
    public static java.util.function.Function<Student1,Student2> changeToStu2() {
        return stu1->Student2.builder()
                .name2(stu1.getName1())
                .age2(stu1.getAge1())
                .sex2(stu1.getSex1()).build();

    }
}
