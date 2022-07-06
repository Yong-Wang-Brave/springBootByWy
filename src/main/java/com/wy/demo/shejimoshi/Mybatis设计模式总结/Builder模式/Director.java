package com.wy.demo.shejimoshi.Mybatis设计模式总结.Builder模式;

public class Director {//工程总监

     private Builder builder;

     public Director(Builder builder) {
         this.builder = builder;
     }

     public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public Building direct() {
        System.out.println("=====工程项目启动=====");
        // 第一步，打好地基；
        builder.buildBasement();
        // 第二步，建造框架、墙体；
        builder.buildWall();
        // 第三步，封顶；
       builder.buildRoof();
        System.out.println("=====工程项目竣工=====");
        return builder.getBuilding();
   }

}