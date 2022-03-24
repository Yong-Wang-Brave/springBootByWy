package com.wy.demo.enumDemo.枚举工具类;


import java.util.Objects;

public interface EnumAbility<T> {

    T getCode();

    String getDescription();
    //是否匹配
    default boolean codeEquals(T enumCode){
        if ((enumCode==null)) {
            return false;
        }
        if ((enumCode instanceof  String)) {
            return ((String) enumCode).equalsIgnoreCase((String)getCode());
        }else{
            return Objects.equals(this.getCode(),enumCode);
        }
    }
//对比两个枚举项是否完全相同
    default boolean equals(EnumAbility<T> anotherEnum){
        return this==anotherEnum;
    }


}
