package com.wy.demo.Reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description: 反射
 * @author: Mr_VanGogh
 * @date: 2019/2/20 下午2:52
 */
public class Reflect {

    private String name;
    private int age;

    private Reflect(int age) {
        this.age = age;
    }

    private void speak(String name) {
        System.out.println("My name is" + name);
    }

    public Reflect(String name) {
        this.name = name;
    }

    public static void main(String[] args)  throws Exception {

        Reflect reflect = new Reflect("a");

        Method[] methods = Reflect.class.getMethods();
        Field[] fields = Reflect.class.getDeclaredFields();

        for (int i = 0; i < fields.length; i ++) {
            fields[i].setAccessible(true);
            System.out.println(fields[i].getName());
        }

        for (int j = 0; j < methods.length; j ++) {
            methods[j].setAccessible(true);
            System.out.println(methods[j].getName());

            methods[j].invoke(reflect);
            System.out.println(methods[j].getName());
        }
    }
}