package com.wy.demo.shejimoshi.观察者模式20221012.方法2;

import com.wy.demo.shejimoshi.观察者模式20221012.Observer;

import java.util.ArrayList;
import java.util.List;

public interface NewSubject {

    List<Observer> list = new ArrayList<>();

    default void registerObserver(Observer o) {
        list.add(o);
    }

    default void nofityAllObserver(String orderNo) {
        list.forEach(c -> c.notify(orderNo));
    }
}