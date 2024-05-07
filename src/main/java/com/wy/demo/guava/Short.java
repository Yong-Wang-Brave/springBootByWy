package com.wy.demo.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;


/**
 * 参考地址https://mp.weixin.qq.com/s/Zy6mR6IUr2G7sYTMsSvnKA
 */
public class Short {

    //连接器
    private static final Joiner joiner = Joiner.on(",").skipNulls();
    //分割器
    private static final Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();

    private  static final CharMatcher charMatcher = CharMatcher.digit();
    private  static final CharMatcher charMatcherAny = CharMatcher.any();


    public static void main(String[] args) {
        //自动去空
        method1();
        //字符串替换

        method2();

    }

    private static void method2() {
        System.out.println("method2..............................................................s");
       String aabbcc344ff = charMatcher.retainFrom("aabbcc344ff");
        System.out.println(aabbcc344ff);
        String aabbcc344ff1 = charMatcher.removeFrom("a,ass44555gg5");
        System.out.println(aabbcc344ff1);
        String s = CharMatcher.inRange('a', 'f').replaceFrom("abcdefght", "*");
        System.out.println(s);

    }

    private static void method1() {
        String join = joiner.join(Lists.newArrayList("a", null, "b"));
        System.out.println("method1join:"+ join);
        for (String s : splitter.split("a,    ,b,,")) {
            System.out.println("?"+s+"?s");
        }
    }
}
