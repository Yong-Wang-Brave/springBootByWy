package com.wy.demo.shejimoshi.代理模式;

public class Client {
     public static void main(String[] args) {
         Internet proxy = new RouterProxy();//实例化的是代理
        proxy.access("http://www.电影.com");
        proxy.access("http://www.游戏.com");
         proxy.access("ftp://www.学习.com/java");
         proxy.access("http://www.工作.com");

         /* 运行结果
           拨号上网...连接成功！
            禁止访问：http://www.电影.com
            禁止访问：http://www.游戏.com
           正在访问：ftp://www.学习.com/java
            正在访问：http://www.工作.com
        */
   }
}