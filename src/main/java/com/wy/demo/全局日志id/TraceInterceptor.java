package com.wy.demo.全局日志id;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.wy.demo.utils.NetworkUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
@Component
@Slf4j
public class  TraceInterceptor  implements HandlerInterceptor {
private static ThreadLocal<String> traceId2=new TransmittableThreadLocal<>();
private ThreadLocal<String> clientHost =new ThreadLocal<>();

public static String getTraceId(){
    return traceId2.get();
}



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
String traceId= UUID.randomUUID().toString().replaceAll("-","");
        MDC.put("traceId",traceId);
       //SnowflakeIdUtil.netxId();
        traceId2.set(traceId);
       // clientHost.set(NetworkUtil.getIpAddress(request));
        clientHost.set(NetworkUtil.getLocalHostIpAddress().getHostAddress());
        log.info("uuid:[{}],host{}",traceId2.get(),clientHost.get());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
     MDC.remove("traceId");
        if ((ex!=null)) {
            log.error("[{}]",traceId2.get());
        }else {
            log.info("[{}]你妹2",traceId2.get());
        }
        traceId2.remove();
        clientHost.remove();
    }
}
