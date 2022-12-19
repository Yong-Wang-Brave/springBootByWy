package com.wy.demo.设计模式.工厂模式.工厂方法模式;

public class FactoryTest {
    public static void main(String[] args) {
        PythonCourseFactory pythonCourseFactory = new PythonCourseFactory();
        pythonCourseFactory.create().record();
    }
}
