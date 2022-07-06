package com.wy.demo.shejimoshi.Builder模式.提升版本;

import com.wy.demo.shejimoshi.Builder模式.常规写法.DerFloor;

public class Builder {


    public  IMenu getDerFloor(){
        return new ImproveImeanu().appendFloor(new DerFloor());
    }


}
