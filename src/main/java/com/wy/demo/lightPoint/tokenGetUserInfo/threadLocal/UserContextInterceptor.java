package com.wy.demo.lightPoint.tokenGetUserInfo.threadLocal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
/**
 * 通过拦截器放到了从request获取信息放到了threadLocal里面。
 */
public class UserContextInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId=(String)request.getHeader("userId");
        String nickname=(String)request.getAttribute("nickname");
        UserContext.UserInfo userInfo = new UserContext.UserInfo().setUserId(userId).setNickname(nickname);
        UserContext.setUserInfo(userInfo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    UserContext.remove();
    }
}
