package com.wy.demo.shejimoshi.getBeansOfType;

import com.wy.demo.shejimoshi.策略模式22.Car;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Benci implements Cars {
    @Override
    public List<Car> getByName(String name) {
        List<Car> arrayList = new ArrayList();
        arrayList.add(new Car("benci","14"));
        return arrayList;
    }
}
