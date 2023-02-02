package com.wy.demo.java8.Function;

import java.util.function.Function;

/**
 * 实现拼接功能
 */
public class FunctionTet {
    public static void main(String[] args) {
        Function<String,String> before =s1->"www."+s1;
        System.out.println(before);
        Function<String,String> after =s1->s1+".com";
        System.out.println(after);
        String wy = before.andThen(after).apply("wy");
        System.out.println(wy);
    }
}
