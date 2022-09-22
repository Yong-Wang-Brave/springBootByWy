package com.wy.demo.lightPoint.tokenGetUserInfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.wy.demo.Exception.Exception2.HealthManageException;
import com.wy.demo.utils.NetworkUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
public class SecurityFilter2 extends OncePerRequestFilter {
    @Resource
    private UserClient userClient;

    @Value("${api.usercenter.call.enable}")
    //开发环境不会调用户中心所以开发环境设置成false
    private Boolean callUsercenter;


    @Override
    /**
     * 根据token调用户中心获取信息放到request
     * 1） 可以通过注解直接从request取
     * 2） 获取通过拦截器放到threadLocal  从threadLocal取。
     */
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            String usertoken = request.getHeader("USERTOKEN");
            if (StrUtil.isEmpty(usertoken)) {
                usertoken = request.getHeader("web-token");

                if (StrUtil.isEmpty(usertoken)) {
                    throw new HealthManageException("5001", "登录状态已失效，请重新登录！");
                }
            }
            //调用用户中心接口判断是否是登录状态，成功时肯定有返回值，失败时会抛出异常
            UserCenterUserInfo userInfo = getUserInfo(request, usertoken);
            BeanUtil.beanToMap(userInfo).forEach(request::setAttribute);
        } catch (Exception e) {
            responseException(response, e);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void responseException(HttpServletResponse response, Exception e) throws IOException {
        log.error("SecurityFilter doFilterInternal 执行异常! ", e);
        HealthManageResult<Object> result = new HealthManageResult<>();
        if (e instanceof HealthManageException) {
            result.setRet(((HealthManageException) e).getCode());
        } else {
            result.setRet("5000");
            result.setMsg(e.toString());
        }
        response.setHeader("content-type", "application/json;charset=UTF-8");
        //将字符转换成字节数组，指定以UTF-8编码进行转换
        byte[] dateByteArr = JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        //使用OutputStream流向客户端输出字节数组
        response.getOutputStream().write(dateByteArr);
    }

    private UserCenterUserInfo getUserInfo(HttpServletRequest request, String usertoken) {
        UserCenterUserInfo userInfo;
        if (callUsercenter) {
            userInfo = userClient.getUserInfo(usertoken);
        } else {
            userInfo = new UserCenterUserInfo();
            String ipAddress = NetworkUtil.getIpAddress2(request);
            if (Objects.equals("127.0.0.1", ipAddress)) {
                try {
                    ipAddress = NetworkUtil.getLocalHostIpAddress().getHostAddress();
                } catch (UnknownHostException e) {
                    log.error("无法拿到正确的ip地址");
                    ipAddress = RandomUtil.randomNumbers(12);
                }
            }
            String requestIp = StrUtil.replace(ipAddress, ".", "");
            userInfo.setUserId(Objects.isNull(requestIp)?"127.0.0.1":null);
            userInfo.setPhone("12233223323");

        }
        log.info("UserCenterUserInfo: {}", JSON.toJSONString(userInfo));
        return userInfo;
    }
}
