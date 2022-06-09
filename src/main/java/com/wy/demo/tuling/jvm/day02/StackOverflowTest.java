package com.wy.demo.tuling.jvm.day02;

// JVM设置  -Xss128k(默认1M)
//线程里面会不停的存放redo方法的栈帧，当栈帧满了，就会抛出StackOverflowError异常
public class StackOverflowTest {
    
    static int count = 0;
    
    static void redo() {
        count++;
        redo();
    }

    public static void main(String[] args) {
        try {
            redo();
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println(count);
        }
    }
}