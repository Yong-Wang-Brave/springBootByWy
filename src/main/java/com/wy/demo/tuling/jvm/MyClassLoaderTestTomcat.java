/*
package com.wy.demo.tuling.jvm;


import java.io.FileInputStream;
import java.lang.reflect.Method;

public class MyClassLoaderTestTomcat {
 static class MyClassLoader extends ClassLoader {
 private String classPath;

 public MyClassLoader(String classPath) {
 this.classPath = classPath;
 }

 private byte[] loadByte(String name) throws Exception {
 name = name.replaceAll("\\.", "/");
 FileInputStream fis = new FileInputStream(classPath + "/" + name
 + ".class");
 int len = fis.available();
 byte[] data = new byte[len];
 fis.read(data);
 fis.close();
 return data;
 }

 @Override
 protected Class<?> findClass(String name) throws ClassNotFoundException {
 try {
byte[] data = loadByte(name);
 //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组。
 return defineClass(name, data, 0, data.length);
 } catch (Exception e) {
 e.printStackTrace();
 throw new ClassNotFoundException();
 }
 }

  */
/**
    * 重写类加载方法，实现自己的加载逻辑，不委派给双亲加载
    * @param name
    * @param resolve
    * @return
    * @throws ClassNotFoundException
    *//*

  @Override
  protected Class<?> loadClass(String name, boolean resolve)
throws ClassNotFoundException {
   synchronized (getClassLoadingLock(name)) {
     // First, check if the class has already been loaded
     Class<?> c = findLoadedClass(name);

     if (c == null) {
       //If still not found, then invoke findClass in order
      // to find the class.
      long t1 = System.nanoTime();
      //不是自定义的依旧双亲委派类
      if (!name.startsWith("com.wy.demo")) {
       c = this.getParent().loadClass(name);
      }else{
       c = findClass(name);

      }

      // this is the defining class loader; record the stats

      sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
      sun.misc.PerfCounter.getFindClasses().increment();
      }
     if (resolve) {
      resolveClass(c);
      }
     return c;
    }
   }
 }







 public static void main(String args[]) throws Exception {
 //初始化自定义类加载器，会先初始化父类ClassLoader，其中会把自定义类加载器的父加载器设置为应用程序类加载器AppClassLoader
 MyClassLoader classLoader = new MyClassLoader("D:/test");
 //D盘创建 test/com/tuling/jvm 几级目录，将User类的复制类User1.class丢入该目录
 Class clazz = classLoader.loadClass("com.wy.demo.tuling.jvm.MathTomcat");
 Object obj = clazz.newInstance();
 Method method = clazz.getDeclaredMethod("sout", null);
 method.invoke(obj, null);
 System.out.println(clazz.getClassLoader().getClass().getName());
 //模拟tomcat打破双亲委派的机制,加载同一个对象三十结果不同
  MyClassLoader classLoader11 = new MyClassLoader("D:/test11");
  Class clazz11 = classLoader11.loadClass("com.wy.demo.tuling.jvm.MathTomcat");
  Object obj11 = clazz11.newInstance();
  Method method11 = clazz11.getDeclaredMethod("sout", null);
  method11.invoke(obj11, null);
  System.out.println(clazz11.getClassLoader().getClass().getName());
 }


 }
*/
