package com.wy.demo.hutool;

import cn.hutool.core.bean.copier.BeanToBeanCopier;
import com.wy.demo.java8.lambda.peek.DTO.Student1;
import com.wy.demo.java8.lambda.peek.DTO.Student2;

public class CopyTest {
    public static void main(String[] args) {
        Student1 student1 = new Student1();
        student1.setAge1("1");
        Student2 student2 = new Student2();
//对象之间相同字段的拷贝
 new BeanToBeanCopier(student1, student2, null, null).copy();
        System.out.println(student2);
    }
}
