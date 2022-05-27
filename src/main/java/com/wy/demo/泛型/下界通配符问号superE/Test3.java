package com.wy.demo.泛型.下界通配符问号superE;

import com.wy.demo.泛型.问号无界通配符.Animal;

import java.util.ArrayList;
import java.util.List;
//在类型参数中使用 super 表示这个泛型中的参数必须是 E 或者 E 的父类。
public class Test3 {
    private <T> void test(List<? super T> dst, List<T> src){
        for (T t : src) {
            dst.add(t);
        }
    }

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();
        new Test3().test(animals,dogs);
    }
    // Dog 是 Animal 的子类
    class Dog extends Animal {

    }
}
