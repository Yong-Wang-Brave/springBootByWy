package com.wy.demo.shejimoshi.观察者模式20221012;

import java.util.ArrayList;
import java.util.List;

public class SubjectImpl implements Subject {
    private final List<Observer> list = new ArrayList<>();
    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }
    @Override
    public void notifyAllObserver(String orderNo) {
        list.forEach(c -> c.notify(orderNo));
    }
}