package com.wy.demo.Exception;

public class ServiceException extends  RuntimeException{
    private String code="999";
    private String msg;
    public ServiceException(String msg){
        super(msg);
        this.msg=msg;
    }
    public ServiceException(String msg,Throwable e){
        super(msg,e);
        this.msg=msg;
    }
    public ServiceException(String code,String msg){
        super(msg);
        this.msg=msg;
        this.code=code;
    }

    public ServiceException(String code,String msg, Throwable e){

        super(msg,e);
        this.msg=msg;
        this.code=code;

    }

}
