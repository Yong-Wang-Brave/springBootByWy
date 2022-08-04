package com.wy.demo.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public final class NetworkUtil {

    //获取请求主机ip,如果通过代理进来，则透过防火墙获取真实ip地址
    public static String getIpAddress(HttpServletRequest request){
        //获取请求主机的IP地址，如果通过代理进来，则透过防火墙获取真实IP地址
        String ip=request.getHeader("X-Forwarded-For");
        if (log.isDebugEnabled()) {
            log.debug("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip="+ip);
        }

        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (log.isDebugEnabled()) {
                    log.debug("getIpAddress(HttpServletRequest) - Proxy-Client-IP -String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (log.isDebugEnabled()) {
                    log.debug("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP -String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (log.isDebugEnabled()) {
                    log.debug("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP -String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (log.isDebugEnabled()) {
                    log.debug("getIpAddress(HttpServletRequest) - getRemoteAddr -String ip=" + ip);
                }
            }


        }else if(ip.length()>15){
            String[] ips = ip.split(",");
            for (String strIp : ips) {
                if (!"unknown".equalsIgnoreCase(strIp)) {
                    ip=strIp;
                    break;
                }

            }

        }

      return  ip;

    }
}
