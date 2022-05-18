package com.wy.demo.lightspot.UnitedReturn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {

    private Integer  code;
    private String message;
    private Object data;
    public Result(ResultCode resultCode,Object data){

        this.code=resultCode.getCode();
        this.message=resultCode.getMessage();
        this.data=data;
    }

    //优化升级
    public static Result sucess(){
        Result result = new Result();
        result.setCode(ResultCode.SUCESS.getCode());

        return  result;
    }
    public static Result sucess(Object data){
        Result result = new Result();
        result.setCode(ResultCode.SUCESS.getCode());
        result.setMessage(ResultCode.SUCESS.getMessage());
        result.setData(data);
        return  result;
    }
    //返回失败
    public static Result failure(ResultCode resultCode){
        Result result = new Result();
        result.setCode(ResultCode.SUCESS.getCode());
        return result;
    }
    public static Result failure(){
        Result result = new Result();
        result.setCode(ResultCode.SUCESS.getCode());
        return result;
    }

    //返回失败
    public static Result failure(ResultCode resultCode,Object data){
        Result result = new Result();
        result.setCode(ResultCode.SUCESS.getCode());
        result.setData(data);
        return result;
    }
}
