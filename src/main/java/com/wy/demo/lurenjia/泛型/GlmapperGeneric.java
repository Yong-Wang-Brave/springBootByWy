package com.wy.demo.lurenjia.泛型;

import com.wy.demo.泛型.问号无界通配符.Animal;
import com.wy.demo.泛型.问号无界通配符.Dog;

import java.util.ArrayList;
import java.util.List;

public class GlmapperGeneric<T> {
    private T t;
    public void set(T t) { this.t = t; }
    public T get() { return t; }

    /**
    * 不指定类型
    */
    public void noSpecifyType(){
        GlmapperGeneric glmapperGeneric = new GlmapperGeneric();
        glmapperGeneric.set("test");
        // 需要强制类型转换
        String test = (String) glmapperGeneric.get();
        System.out.println(test);
    }
    /**
    * 指定类型
    */
    public void specifyType(){
        GlmapperGeneric<String> glmapperGeneric = new GlmapperGeneric();
        glmapperGeneric.set("test");
        // 不需要强制类型转换
        String test = glmapperGeneric.get();
        System.out.println(test);
    }

    static int countLegs (List<? extends Animal > animals ) {
        int retVal = 0;
        for ( Animal animal : animals )
        {
            retVal += animal.countLegs();
        }
        return retVal;
    }
    static int countLegs1 (List<Animal> animals ){
        int retVal = 0;
        for ( Animal animal : animals )
        {
            retVal += animal.countLegs();
        }
        return retVal;
    }
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        // 不会报错
        countLegs( dogs );
        // 报错
        //scountLegs1(dogs);
    }
}