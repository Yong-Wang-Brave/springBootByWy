package com.wy.demo.设计模式.工厂模式.工厂方法模式;


public class JavaCourseFactory implements IcourseFactory{

    @Override
    public Icourse create() {
        return new JavaCourse();
    }


}
