package com.wy.demo.亮点.自定义注解实现统一返回;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.Handler;
import java.lang.reflect.Method;

//请求拦截器
@Slf4j
@Component
public class ResponseResultInterceptor  implements HandlerInterceptor {
//标记名字
    public  static final  String RESPONSE_RESULT_ANN="RESPONSE_RESULT_ANN";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求的方法
        if ((handler instanceof HandlerMethod)) {
            final HandlerMethod handlerMethod=(HandlerMethod) handler;
            final  Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            //判断是否在类上添加了注解
            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                //设置此请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(RESPONSE_RESULT_ANN,clazz.getAnnotation(ResponseResult.class));
            }else if (method.isAnnotationPresent(ResponseResult.class)){//方法体上是否有注解
                //设置此请求返回体，需要包装。。。。
                request.setAttribute(RESPONSE_RESULT_ANN,method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
/*    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object hanlder)
    throws Exception{
        System.out.println("");
        //请求的方法
        if ((hanlder instanceof HandlerMethod)) {
            final HandlerMethod handlerMethod=(HandlerMethod) hanlder;
           final  Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            //判断是否在类上添加了注解
            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                //设置此请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(RESPONSE_RESULT_ANN,clazz.getAnnotation(ResponseResult.class));
            }else if (method.isAnnotationPresent(ResponseResult.class)){//方法体上是否有注解
                //设置此请求返回体，需要包装。。。。
                request.setAttribute(RESPONSE_RESULT_ANN,method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }*/

}
