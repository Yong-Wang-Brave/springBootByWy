package com.wy.demo.validGroup;

import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
 
/**
 * 全局异常处理
 */
//@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
 
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public String handlerUnexpectedTypeException(BindException ex){
        BindingResult result = ex.getBindingResult();
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            if (fieldError != null) {
                return fieldError.getDefaultMessage();
            }
        }
        return "失败，请刷新重试";
    }
 
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handlerException(Exception ex){
        ex.printStackTrace();
        return ex.getMessage();
    }



}