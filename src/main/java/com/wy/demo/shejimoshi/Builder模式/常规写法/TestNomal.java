package com.wy.demo.shejimoshi.Builder模式.常规写法;

public class TestNomal {

    static String testUse(String type) {

        if (type.equals(new DerFloor().scene())) {
            System.out.println("德尔");
            return "德尔地板";
        } else if (type.equals(new ShengXiangFloor().scene())) {
            return "圣象地板";
        }
return null;

    }

    public static void main(String[] args) {
        testUse("德尔地板");
    }
}
