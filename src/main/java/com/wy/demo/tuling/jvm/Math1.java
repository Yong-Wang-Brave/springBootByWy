package com.wy.demo.tuling.jvm;

import com.wy.demo.entity.User;

import java.util.ArrayList;
import java.util.List;

import static rx.schedulers.Schedulers.test;

public class Math1 {
 public static final int initData = 666;
 //默认使用同一种加载器也就是全盘负责委托机制
/* public static User user = new User();*/
static{
 test1();
 }

 private static void test1() {
  System.out.println("test1");
 }

 public int sout() { //一个方法对应一块栈帧内存区域
 int a = 1;
 int b = 2;
 int c = (a + b) * 10;
return c;
 }


/*public static void main(String[] args) {
  List ls =new ArrayList<>();
 Math math = new Math();

 //在classLoader的522行deug 定义 name.equals("类的全路径") ，可以直接运行到符合条件的断点处
 math.compute();
 }*/

 }
