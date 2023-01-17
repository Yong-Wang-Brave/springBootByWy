package com.wy.demo.Exception;

import cn.hutool.http.HttpStatus;
import com.wy.demo.Exception.Exception2.HealthManageException;
import com.wy.demo.lightPoint.tokenGetUserInfo.HealthManageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackages = {"com.wy.demo.controller"})
public class GlobalExceptionHandler20231017 {

    /**
     * 处理自定义异常
     * @param httpServletRequest
     * @param e
     * @return
     */
    @ExceptionHandler(HealthManageException.class)
    public HealthManageResult<Object> handleServiceException(HttpServletRequest  httpServletRequest,HealthManageException e){
        return HealthManageResult.error(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public HealthManageResult<Object> handlerNoFoundException(HttpServletRequest httpServletRequest, NoHandlerFoundException e){
        return HealthManageResult.error(String.valueOf(HttpStatus.HTTP_NOT_FOUND),"路径不存在，请检查路径是否正确");
    }
   //主键冲突
    @ExceptionHandler(DuplicateKeyException.class)
    public HealthManageResult<Object> handleDuplicateKeyException(HttpServletRequest httpServletRequest, DuplicateKeyException e){
        return HealthManageResult.error("数据库已存在该记录");
    }
    //数字格式转换异常
    @ExceptionHandler(DateTimeParseException.class)
    public HealthManageResult<Object> handleDuplicateKeyException(HttpServletRequest httpServletRequest, DateTimeParseException e){
        return HealthManageResult.error("参数中日期字段格式错误");
    }

    //参数格式异常  不会进入controller方法
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public HealthManageResult<Object> handleHttpMessageNotReadableException(HttpServletRequest httpServletRequest, HttpMessageNotReadableException e){
       String errorMsg="参数格式异常!";
       log.error(errorMsg,e);
        return HealthManageResult.error(errorMsg+e.getCause());
    }
    //操作文件异常
    @ExceptionHandler(IOException.class)
    public HealthManageResult<Object> handleInvalidFormatException(HttpServletRequest httpServletRequest, IOException e){
        return HealthManageResult.error("操作文件发生异常! ");
    }

    //参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HealthManageResult<Object> handleMethodArgumentNotValidException(HttpServletRequest httpServletRequest, MethodArgumentNotValidException e){

        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("!"));

        return HealthManageResult.error(message);
    }


    //参数校验异常
    @ExceptionHandler(BindException.class)
    public HealthManageResult<Object> handleBindException(HttpServletRequest httpServletRequest, BindException e){

        String message = e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("!"));

        return HealthManageResult.error(message);
    }

    @ExceptionHandler(Exception.class)
    public HealthManageResult<Object> handleException(HttpServletRequest httpServletRequest, Exception e){
        String errorMsg ="服务器发生错误，请联系管理员";
        log.error(errorMsg,e);
        return HealthManageResult.error(errorMsg+e.toString());
    }



}
