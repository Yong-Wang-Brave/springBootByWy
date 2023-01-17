package com.wy.demo.Exception.Exception2;


import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.wy.demo.lightPoint.tokenGetUserInfo.HealthManageResult;
import com.wy.demo.lightspot.UnitedReturn.Result2;
import com.wy.demo.utils.validate.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

//@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler2 {


    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ServiceeException.class)
    @Order(value = 1)
    public Result<Object> handleServiceException(HttpServletRequest httpServletRequest, ServiceeException e) {
        log.error(e.getMsg(), e);
        return Result.error(e.getCode(), e.getMsg());
    }


    /**
     * 路径错误异常
     *
     * @param httpServletRequest
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<Object> handlerNoFoundException(HttpServletRequest httpServletRequest, NoHandlerFoundException e) {
        String msg = "路径不存在，请检查路径是否正确";
        log.error(msg, e);
        return Result.error(String.valueOf("404"), msg);
    }


    /**
     * 其他异常
     *
     * @param httpServletRequest
     * @param e
     * @return
     */
 //   @ExceptionHandler(ConstraintViolationException.class)
    public Result<Object> constraintViolationException(HttpServletRequest httpServletRequest, ConstraintViolationException e) {
        String message = "卡号异常";
        log.error(message, e);
        return Result.error(message);

    }
    /**
     * 其他异常
     *
     * @param httpServletRequest
     * @param e
     * @return
     */
    @ExceptionHandler(HystrixRuntimeException.class)
    public Result<Object> handleHystrixRuntimeException(HttpServletRequest httpServletRequest, HystrixRuntimeException e) {
        String message = "服务器发生错误，请联系管理员";
        log.error(message, e);
        return Result.error(message + e.toString());

    }

    /**
     * 参数校验异常
     *
     * @param httpServletRequest
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Result<Object> handleException(HttpServletRequest httpServletRequest, BindException e) {
        String message = e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("!"));
        log.error(message, e);
        return Result.error(message);

    }

/**
     * 参数校验异常   方式一
     *
     * @param httpServletRequest
     * @param e
     * @return
    */
    @ExceptionHandler({ConstraintViolationException.class,MethodArgumentNotValidException.class})
    public Result<Object> handleException(HttpServletRequest httpServletRequest, MethodArgumentNotValidException e) {
    //常规写法
        /*    String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("!"));
        log.error(message, e);*/
        //整合
        return Result.error(ValidateUtil.handValidationException(e).getMeaasge());
    }
    //方式二
/*

    @ExceptionHandler({ConstraintViolationException.class,MethodArgumentNotValidException.class,
            ServletRequestBindingException.class, ValidationException.class,
            UnexpectedException.class,BindException.class
    })
    public Wrapper handleException1(HttpServletRequest request, Exception e) {
log.error("[{}]--handleException--host{} invokes url{} ERROR:{}", TraceInterceptor.getTraceId(),
        request.getRemoteHost(),request.getRequestURL(),e);

        return ValidateUtil.handValidationException(e);
    }
*/





    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result2 handlerException(MismatchedInputException ex){
        log.error("Exception{}",ex.getMessage());
        return Result2.failure(ex.getMessage());
    }


    /**
     *
      * @param httpServletRequest
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(HealthManageException.class)
    public HealthManageResult<Object> handleServiceException(HttpServletRequest  httpServletRequest,HealthManageException e){
        return  HealthManageResult.error(e.getCode(),e.getMsg());
    }


    /**
     * 其他异常
     *
     * @param httpServletRequest
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result<Object> handleException(HttpServletRequest httpServletRequest, Exception e) {
        String message = "服务器发生错误，请联系管理员";
        log.error(message, e);
        return Result.error(message + e.toString());

    }
}
