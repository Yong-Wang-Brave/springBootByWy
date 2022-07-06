package com.wy.demo.shejimoshi.Mybatis设计模式总结.Builder模式;

public interface Builder {//施工方接口

     public void buildBasement();

     public void buildWall();

     public void buildRoof();

     public Building getBuilding();
}