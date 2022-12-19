package com.wy.demo.设计模式.工厂模式.简单工厂;

public class EasyFactoryTest {
    public static void main(String[] args) {
        CourseFactory courseFactory = new CourseFactory();
        courseFactory.create(JavaCourse.class).record();

    }
}
