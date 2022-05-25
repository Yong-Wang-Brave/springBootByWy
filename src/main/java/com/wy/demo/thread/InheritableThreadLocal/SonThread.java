package com.wy.demo.thread.InheritableThreadLocal;
//链接；https://mp.weixin.qq.com/s/ITrKP3HkWK1uQ0nUme2tmg

/*原理：当inheritThreadLocals的值为true并且其父线程的inheritableThreadLocals不为null时,
        把其父线程inheritableThreadLocals 赋值给当前线程的inheritableThreadLocals*/

public class SonThread {
    public static void main(String[] args) throws InterruptedException {
        Thread parentParent = new Thread(() -> {
            ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
            threadLocal.set(1);
            InheritableThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<>();
            inheritableThreadLocal.set(2);

            new Thread(() -> {
                System.out.println("threadLocal=" + threadLocal.get());
                System.out.println("inheritableThreadLocal=" + inheritableThreadLocal.get());
            }).start();
        }, "父线程");
        parentParent.start();
    }
}
