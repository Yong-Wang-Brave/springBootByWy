package com.wy.demo.亮点.自定义注解实现统一返回;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result2<T> {
public static  final  String SUCCESS_CODE ="0000";
public static final String SERVICE_ERROR_CODE="9999";
    private String  ret;
    private String msg;
    private T data;

    //优化升级
    public static <T> Result2<T> sucess(){
        return  sucess("成功");
    }
    public static <T> Result2<T> sucess(String  msg){
        return sucess(msg,null);
    }
    public static <T> Result2<T> sucess(T data){
    return sucess("成功",data);
    }

    public static <T>Result2<T> sucess(String msg,T data){

        return  new Result2<>(SUCCESS_CODE,msg,data);
    }
public static <T> Result2<T> sucess(String code,String msg,T data){
    return  new Result2<>(code,msg,data); }

    public  static <T> Result2<T>  failure(){
        return failure("失败");
    }

    public static <T> Result2 failure(String msg){
        return failure(SERVICE_ERROR_CODE,msg);
    }
    public static <T> Result2<T> failure(String ret,String msg){
        return new Result2<>(ret,msg,null);
    }
}
