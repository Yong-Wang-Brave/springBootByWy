package com.wy.demo.lightPoint.tokenGetUserInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HealthManageResult<T> {
    public static final String SUCCESS_CODE="0";
    public static  final String SERVER_ERROR_CODE="9999";
    private String ret;
    private String msg;
    private T data;

    public static <T> HealthManageResult<T> ok(){return ok("成功");}
    public static <T> HealthManageResult<T> ok(String msg ){return ok(msg,null);}
    public static <T> HealthManageResult<T> ok(T data ){return ok("成功",data);}
    public static <T> HealthManageResult<T> ok(String msg,T data ){return new HealthManageResult<>(SUCCESS_CODE,msg,data);}
    public static <T> HealthManageResult<T> ok(String code,String msg,T data ){return new HealthManageResult<>(code,msg,data);}


        public static <T> HealthManageResult<T> error(){return error("失败");}

        public static<T> HealthManageResult<T> error(String msg ){return error(SERVER_ERROR_CODE,msg);}

        public static<T> HealthManageResult<T> error(String ret,String msg ){return
        new HealthManageResult<>(ret,msg,null);
        }

     public Boolean isSucess() {return Objects.equals(SUCCESS_CODE,this.getRet());}

}
