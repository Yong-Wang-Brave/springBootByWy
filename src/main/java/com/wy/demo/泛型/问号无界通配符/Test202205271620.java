package com.wy.demo.泛型.问号无界通配符;

import java.util.ArrayList;
import java.util.List;

public class Test202205271620 {
    static int countLegs (List<? extends Animal> animals ) {
        int retVal = 0;
        for ( Animal animal : animals )
        {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    static int countLegs1 (List<Animal> animals ){
        int retVal = 0;
        for (Animal animal : animals)
        {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        // 不会报错
        countLegs(dogs);
        // 报错
     //   countLegs1(dogs);
    }

}
