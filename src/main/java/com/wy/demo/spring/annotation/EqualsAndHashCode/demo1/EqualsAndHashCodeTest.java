package com.wy.demo.spring.annotation.EqualsAndHashCode.demo1;


//参考链接：https://blog.csdn.net/Rookie_cc/article/details/118415024
public class EqualsAndHashCodeTest {
 //
    public static void main(String[] args) {
        BYD bydBlue = new BYD();
        bydBlue.setPrice(150000);
        bydBlue.setColor("蓝色");
        bydBlue.setEndurance(1200);
 
        BYD bydWhite = new BYD();
        bydWhite.setPrice(150000);
        bydWhite.setColor("白色");
        bydWhite.setEndurance(1200);
        System.out.println("bydBlue:" + bydBlue.hashCode());
        System.out.println("bydWhite:" + bydBlue.hashCode());
//说明：两个对象equals返回true ,那么hashcode一定相等；两个对象的hashcode相等，那么equals不一定为true
        //重写equals方法，则hashCode（）方法也必须重写，这样才能保证equals返回true,hashcode的值也相等。
        System.out.println("两个对象比较结果:" + bydBlue.equals(bydWhite));
    }
 
}