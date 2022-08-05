package com.wy.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

@Slf4j
public final class NetworkUtil {

    //获取请求主机ip,如果通过代理进来，则透过防火墙获取真实ip地址
    public static String getIpAddress(HttpServletRequest request) {
        //获取请求主机的IP地址，如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");
        if (log.isDebugEnabled()) {
            log.debug("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
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


        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (String strIp : ips) {
                if (!"unknown".equalsIgnoreCase(strIp)) {
                    ip = strIp;
                    break;
                }

            }

        }

        return ip;

    }

    //获取真实访问地址
    public static String getIpAddress2(HttpServletRequest request) {
        String unkownStr = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isNotBlank(ip) || unkownStr.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isNotBlank(ip) || unkownStr.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isNotBlank(ip) || unkownStr.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (StringUtils.isNotBlank(ip) || unkownStr.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isNotBlank(ip) || unkownStr.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    //正确的ip拿法，优先拿site-local地址
    public static InetAddress getLocalHostIpAddress() throws UnknownHostException {

        try {
            InetAddress candidateAddress = null;
            //遍历所有的网络接口
            for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                NetworkInterface iface = ifaces.nextElement();
                //在所有接口下载遍历IP
                for (Enumeration<InetAddress> inetAddres = iface.getInetAddresses(); inetAddres.hasMoreElements(); ) {
                    InetAddress inetAddr = inetAddres.nextElement();
                    //排除loopback类型地址
                    if (!inetAddr.isLoopbackAddress()) {
                        if (inetAddr.isSiteLocalAddress()) {
                            //如果是site-local地址，就是它了
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            //site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress == null) {
                return candidateAddress;
            }
            //如果没有发现non-loopback 地址，只能用最次选的方案
            InetAddress jdkSuppliedAddress =InetAddress.getLocalHost();

            if (jdkSuppliedAddress!=null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly return null");
            }

            return jdkSuppliedAddress;
        } catch (Exception e) {
            UnknownHostException unknownHostException =
                    new UnknownHostException("Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }

    }


}
