package com.wy.demo.qiangge.support;

import cn.hutool.core.util.IdUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.wy.demo.utils.NetworkUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminRequestIdHandler implements HandlerInterceptor {

    private static ThreadLocal<String> traceId = new TransmittableThreadLocal<>();
    private static ThreadLocal<String> userID = new TransmittableThreadLocal<>();
    private ThreadLocal<Long> startTimestamp = new ThreadLocal<>();
    private ThreadLocal<String> clientHost = new ThreadLocal<>();

    public static String getTraceId() {
        return traceId.get();
    }

    public static String getUserId() {
        return userID.get();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求入口设置用户名（UM账号）
        HttpSession session = request.getSession();
        userID.set(String.valueOf(session.getAttribute("SUCCESS_LOGIN_USER")));
        //请求入口设置请求开始时间
        startTimestamp.set(System.currentTimeMillis());
        //调用客户端网络ip
        clientHost.set(NetworkUtil.getIpAddress(request));
        //请求入口设置traceId
        traceId.set(String.valueOf(IdUtil.getSnowflakeNextId()));
        log.info("[{}] Accept request: {}, host: {}, userId: {}",traceId.get(),request.getRequestURI(),clientHost.get(),userID.get());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex!=null) {
            log.error("[{}] Request error {},host: {}, take: {}ms,message:",traceId.get(),request.getRequestURI(),clientHost.get(),System.currentTimeMillis()-startTimestamp.get(),ex);
        } else{
            log.info("[{}] End request: {},host:{},take:{}ms",traceId.get(),request.getRequestURI(),clientHost.get(),System.currentTimeMillis()-startTimestamp.get());
        }
        //请求结束后记得清理这些变量
        clientHost.remove();
        traceId.remove();
        userID.remove();
        startTimestamp.remove();
    }
}
