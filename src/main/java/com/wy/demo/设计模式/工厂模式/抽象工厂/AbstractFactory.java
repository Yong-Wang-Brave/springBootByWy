package com.wy.demo.设计模式.工厂模式.抽象工厂;

public class AbstractFactory {
    public static void main(String[] args) {
        JavaCourseFactory javaCourseFactory = new JavaCourseFactory();
        javaCourseFactory.createNote().edit();
        javaCourseFactory.createVideo().record();
    }
}
