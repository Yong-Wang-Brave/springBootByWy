package com.wy.demo.utils.validate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;
import java.rmi.UnexpectedException;
import java.util.stream.Collectors;

@Slf4j
public class ValidateUtil {
    public static Wrapper handValidationException(Exception e){
        String msg="";
        try {
            if(e instanceof UnexpectedException){
                UnexpectedException t =(UnexpectedException) e;
                return Wrapper.error(t.getMessage());
            }
            if(e instanceof ValidationException){
                ValidationException t =(ValidationException) e;
                return Wrapper.error(t.getMessage());
            }
            if(e instanceof MethodArgumentNotValidException){
                MethodArgumentNotValidException t =(MethodArgumentNotValidException) e;
                BindingResult bindingResult = t.getBindingResult();
                return getValidatedWragger(bindingResult);
            }else if(e instanceof BindException){
                BindException t =(BindException) e;
                BindingResult bindingResult = t.getBindingResult();
                return getValidatedWragger(bindingResult);
            }else if(e instanceof ConstraintViolationException){
                ConstraintViolationException t =(ConstraintViolationException) e;
                msg=t.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(","));
            }else if(e instanceof MissingServletRequestParameterException){
                MissingServletRequestParameterException t =(MissingServletRequestParameterException) e;
                msg=t.getParameterName()+" 不能为空";
            }else if(e instanceof MissingPathVariableException){
                MissingPathVariableException t =(MissingPathVariableException) e;
                msg=t.getVariableName()+ " 不能为空";
            }else{
                msg="必填参数缺失";
            }

        } catch (Exception ex) {
           log.error("处理参数校验失败",ex);
        }
        return  Wrapper.error(msg);
    }
//封装校验结果
    private static Wrapper getValidatedWragger(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(","));
            return Wrapper.error(message);

        }
        return  Wrapper.error("");
    }




}
