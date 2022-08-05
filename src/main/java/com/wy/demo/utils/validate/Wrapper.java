package com.wy.demo.utils.validate;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
public class Wrapper implements Serializable {
    //处理成功
    public static final String SUCCESS = "00";
    //处理时发生异常
    public static final String ERROR_CODE = "01";
    //查无数据
    public static  final String NOT_FOUND ="02";
    //错误码：参数非法
    public static  final String ILLEGAL_PARAM = "03";
    //成功信息
    public static  final String SUCESS_MESSAGE="处理成功";
   //查询无数据
   public static  final String NOT_FOUND_MESSAGE="查询无数据";
    //处理异常
    public static  final String ERROR_MESSAGE="处理时发生异常";
    //参数校验失败
    public static  final String ILLEGAL_PARA_MESSAGE="参数校验失败";

    //请求返回码
    private String status;

    //返回信息
    private String meaasge;

    //返回结果
    private Object result;
    private Wrapper(){this(SUCCESS,SUCESS_MESSAGE,null);}

    private Wrapper(String status){this.status(status);}

    private Wrapper(String status,Object result){this.status(status).result(result);}

    private Wrapper(String status,String meaasge){this.status(status).message(meaasge);}

    public Wrapper(String success, String message, Object o) {
        this.status(status).message(message).result(result);
    }

    private Wrapper result(Object result) {
        this.setResult(result);
        return  this;
    }

    private Wrapper message(String message) {
        this.setMeaasge(message);
        return  this;
    }

    //返回自身的引用
    private Wrapper status(String status) {
        this.setStatus(status);
        return this;
    }
    //处理成功
    public static Wrapper ok(){
        return new Wrapper();
    }

    public static Wrapper ok(String meaasge,Object result){
        return new Wrapper(SUCCESS,meaasge,result);
    }

    public static Wrapper ok(String returnCode,String meaasge,Object result){
        return new Wrapper(returnCode,meaasge,result);
    }

    public static Wrapper ok(Object result){return new Wrapper(SUCCESS,result);}
    //处理时发生异常
    public static Wrapper error(String meaasge){
        return new Wrapper(ERROR_CODE,meaasge);
    }
    //..
    //自定义返回
    public static Wrapper result(String returnCode,String message,Object result){
        return new Wrapper(returnCode,message,result);
    }


}
