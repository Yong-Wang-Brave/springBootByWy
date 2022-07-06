package com.wy.demo.shejimoshi.Builder模式.提升版本;

import com.wy.demo.shejimoshi.Builder模式.常规写法.DecorateInterface;

public interface IMenu {

    /**
     * 地板
     */
    IMenu appendFloor(DecorateInterface matter);



}
