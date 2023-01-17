package com.wy.demo.lightPoint.tokenGetUserInfo;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthManageResult<T> {
    public static final String SUCCESS_CODE="0";
    public static  final String SERVER_ERROR_CODE="9999";
    private String ret;
    private String msg;
    private  String sysTime;
    private T data;

    public static <T> HealthManageResult<T> ok(){return ok("成功");}
    public static <T> HealthManageResult<T> ok(String msg ){return ok(msg,null);}
    public static <T> HealthManageResult<T> ok(T data ){return ok("成功",data);}
    public static <T> HealthManageResult<T> ok(String msg,T data ){return ok(SUCCESS_CODE,msg,data);}
    public static <T> HealthManageResult<T> ok(String code,String msg,T data ){return new HealthManageResult<>(code,msg,getFormatSysTime(),data);}

    private static String getFormatSysTime() {
        return LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_PATTERN);
    }


    public static <T> HealthManageResult<T> error(){return error("失败");}

        public static<T> HealthManageResult<T> error(String msg ){return error(SERVER_ERROR_CODE,msg);}

        public static<T> HealthManageResult<T> error(String ret,String msg ){return
        new HealthManageResult<>(ret,msg,getFormatSysTime(),null);
        }

     public Boolean isSucess() {return Objects.equals(SUCCESS_CODE,this.getRet());}

}
