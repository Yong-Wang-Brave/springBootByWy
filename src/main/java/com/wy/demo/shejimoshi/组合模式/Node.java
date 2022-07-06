package com.wy.demo.shejimoshi.组合模式;

public abstract class Node {
     protected String name;//节点命名

     public Node(String name) {//构造节点，传入节点名。
         this.name = name;
     }

    //增加后续子节点方法
     protected abstract void add(Node child);
}