package com.wy.demo.自定义注解.demo2;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor {

    /**
     * preHandle 在请求到达拦截器前触发
     * 这里模拟：
     * 		在请求到达时，只放行未标注@AuthAnnotation注解的方法（返回true）
     *      而不放行标注了@AuthAnnotation注解的方法（返回false）
     * 如果需要更多的功能可以自行添加
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	// 先判断当前传入的是不是HandlerMethod的实例 防止类型转换的错误
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 这里先判断类上有没有@AuthAnnotation注解
        AuthAnnotation authAnnotation = handlerMethod.getBean().getClass().getAnnotation(AuthAnnotation.class);
        Method method = null;
        // 如果类上没有@AuthAnnotation注解 再判断对应的方法上有没有该注解
        if (authAnnotation == null) {
            method = handlerMethod.getMethod();
            authAnnotation = method.getAnnotation(AuthAnnotation.class);
        }

        if (authAnnotation == null) {
            // 方法上没有注解时的操作
            System.out.println("方法 " + method.getName() + " 上未标注@AuthAnnotation 直接放行");
            return true;
        } else {
            // 方法上有注解时的操作
            System.out.println("方法 "+ method.getName() +" 上标注了@AuthAnnotation 不允许放行");
            return false;
        }
    }

    // postHandle 在响应到达拦截器前触发
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// 可以自行编写操作
    }

    // afterCompletion 在页面渲染完毕后触发
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// 可以自行编写操作
    }
}
