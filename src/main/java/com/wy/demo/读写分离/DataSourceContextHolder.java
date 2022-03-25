package com.wy.demo.读写分离;

/**
 * @Author ys 2021/4/8
 * @description   aop 这次切换数据源不仅用到了aop的切面编程,还用到了ThreadLocal用来保存现在使用的是什么数据库
 */
public class DataSourceContextHolder {
  private static final ThreadLocal<String> contextHolder = ThreadLocal.withInitial(() -> "master");

  public static void setDataSourceKey(String key) {
    contextHolder.set(key);
  }

  public static String getDataSourceKey() {
    return contextHolder.get();
  }

  public static void clearDataSourceKey() {
    contextHolder.remove();
  }
}
