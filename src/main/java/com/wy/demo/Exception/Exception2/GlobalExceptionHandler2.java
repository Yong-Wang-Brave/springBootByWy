package com.wy.demo.Exception.Exception2;


import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.wy.demo.Exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler2 {



/**
 * 处理自定义异常
 */
   @ExceptionHandler(ServiceeException.class)
   @Order(value = 1)
    public Result<Object> handleServiceException(HttpServletRequest httpServletRequest, ServiceeException e){
    log.error(e.getMsg(),e);
    return  Result.error(e.getCode(),e.getMsg());
}


    /**
     * 路径错误异常
     * @param httpServletRequest
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<Object> handlerNoFoundException(HttpServletRequest httpServletRequest, NoHandlerFoundException e){
        String msg="路径不存在，请检查路径是否正确";
        log.error(msg,e);
        return Result.error(String.valueOf("404"),msg);
}

    /**
     * 其他异常
     * @param httpServletRequest
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
public Result<Object> handleException(HttpServletRequest httpServletRequest,Exception e){
        String message="服务器发生错误，请联系管理员";
        log.error(message,e);
        return Result.error(message+e.toString());

}

    /**
     * 其他异常
     * @param httpServletRequest
     * @param e
     * @return
     */
    @ExceptionHandler(HystrixRuntimeException.class)
    public Result<Object> handleHystrixRuntimeException(HttpServletRequest httpServletRequest,HystrixRuntimeException e){
        String message="服务器发生错误，请联系管理员";
        log.error(message,e);
        return Result.error(message+e.toString());

    }
}