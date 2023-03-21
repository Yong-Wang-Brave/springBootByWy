package com.wy.demo.自定义注解.demo4;

import java.lang.reflect.Field;

public class ReplaceParser {

    public Object Parse(Object o) {
        Class<?> mClass = null;
        Object o1 = o;
        try {
            mClass = o.getClass();
            //getDeclaredFields方法返回的是一个类中所有声明的字段，无论其访问修饰符是什么。而getFields方法只返回公共访问修饰符修饰的字段。
            Field[] fields = mClass.getDeclaredFields();
            for (Field field : fields) {
                //isAnnotationPresent是Java中的一个方法，它用于判断某个类、方法、字段等是否被某个特定的注解（Annotation）所修饰。
                if (field.isAnnotationPresent(Repalce.class)) {
                    //设置一个类中的私有字段（private Field）可访问。
                    field.setAccessible(true);
                    //获取当前field设置的注解
                    Repalce trimSpace = field.getAnnotation(Repalce.class);
                    String source = trimSpace.source();
                    String target = trimSpace.target();

                    //获取当前field的值进行处理
                    String s = String.valueOf(field.get(o1));
                    String replace = s.replace(source, target);
                    field.set(o1, replace);

                }
            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return o1;
    }

}
