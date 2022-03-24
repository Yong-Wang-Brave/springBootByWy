package com.wy.demo.enumDemo.枚举工具类;

import org.checkerframework.checker.units.qual.C;

import java.util.EnumSet;

public class EnumAbilityUtil {
    //extends代表继承 继承枚举的东西
    public  static  <E extends Enum<E> ,T>EnumAbility<T> getEnumByCode(Class<E> enumClass,T code){
        if (null==code) {
            return null;
        }
       //把枚举所有的实例都遍历出来
        EnumSet<E> es =EnumSet.allOf(enumClass);
        for (E e : es) {
            if (e instanceof EnumAbility) {
                if (((EnumAbility) e).codeEquals(code)) {
                    return (EnumAbility<T>) e;
                }
            }
        }
return null;

    }

}
