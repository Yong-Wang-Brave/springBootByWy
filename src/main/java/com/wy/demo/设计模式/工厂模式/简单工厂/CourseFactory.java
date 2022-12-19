package com.wy.demo.设计模式.工厂模式.简单工厂;

public class CourseFactory {
    public Icourse create(Class<? extends Icourse> clazz){
        try {
            if (null!=clazz) {
                return clazz.newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  null;
    }


}
