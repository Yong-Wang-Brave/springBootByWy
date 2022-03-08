package com.wy.demo.Exception.Exception2;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    /**
     * 成功统一响应状态码
     */
    public static final String SUCESS_CODE = "0";

    public static final String SERVICE_ERROR_CODE = "9999";

    /**
     * 返回状态码，0成功，其他 失败
     */
    private String ret;

    private String msg;

    private T data;

    public static  <T> Result<T> ok(){return ok("成功");}

    public static <T> Result<T> ok(String msg){return ok(msg,null);}
    public static <T> Result<T> ok(T data)  {return ok("成功",data);}
    public static <T> Result<T> ok(String msg,T data){return new Result<>(SUCESS_CODE,msg,data);}

    public static <T> Result<T> ok(String code,String msg,T data){return new Result<>(code,msg,data);}

    public static <T> Result<T> error() {return error("失败");}

    public static <T> Result<T> error(String msg){return error(SERVICE_ERROR_CODE,msg);}

    public static <T> Result<T> error(String ret, String msg){
        return new Result<>(ret,msg,null);
    }






}
