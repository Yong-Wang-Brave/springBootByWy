package com.wy.demo.动态代理;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperStar implements Star{

    private  String  name;

    @Override
    public String sing(String name){
        System.out.println(this.name+"正在唱歌，歌曲名字是"+name);
        return "谢谢各位";
    }
    @Override
    public void dance(){
        System.out.println(this.name+"在跳舞");
    }


}
