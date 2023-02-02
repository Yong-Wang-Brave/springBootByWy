package com.wy.demo.设计模式.单例模式;

/**  双层检查锁
 * 所有获取的都是一个
 * 只能创建一次
 * 私有的构造方法  公共的获取方法
 */
public class DanliTest {
    //私有的构造方法，外部访问不了
    private  DanliTest() {

    }
    private static DanliTest danli =null;
    public DanliTest get(){
        if(danli==null){
            synchronized (DanliTest.class){
                if (danli==null) {
                    danli=new DanliTest();
                }
            }
        }
        return danli;

    }

}
