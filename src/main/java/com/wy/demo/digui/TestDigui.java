package com.wy.demo.digui;

import com.wy.demo.controller.dto.Student;
import com.wy.demo.controller.dto.Student2;

import java.util.ArrayList;
import java.util.List;

/**
 * 后面对变量赋值也会影响到输出结果
 */
public class TestDigui {
    public static void main(String[] args) {
        List<Student2> studentList2 =new ArrayList<>();
        Student student = new Student();
        student.setStudent2List(studentList2);
        System.out.println(student);
        Student2 student2 = new Student2();
        student2.setAge(12);
        studentList2.add(student2);
        System.out.println(student);
        ;
    }
}
