package com.wy.demo.shejimoshi.Builder模式.提升版本;

import com.wy.demo.shejimoshi.Builder模式.常规写法.DecorateInterface;

public class ImproveImeanu implements  IMenu{

    ImproveImeanu() {
    }



    @Override
    public IMenu appendFloor(DecorateInterface matter) {
        return this;
    }
}
