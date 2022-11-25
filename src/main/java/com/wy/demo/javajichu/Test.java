package com.wy.demo.javajichu;

import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        Point hah = new Point(1, 1, "hah");
        Point luelue = new Point(1, 1, "luelue");
        System.out.println(hah.hashCode());
        System.out.println(luelue.hashCode());
        System.out.println(hah.equals(luelue));
        HashSet<Object> objects = new HashSet<>();
        objects.add(hah);
        objects.add(luelue);
        System.out.println(objects.size());
        System.out.println(objects.contains(luelue));
    }
}
