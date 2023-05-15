package com.wy.demo.filter;


import cn.hutool.core.util.StrUtil;
import com.wy.demo.config.feign.Constants;
import org.slf4j.MDC;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String userInfo = servletRequest.getHeader(Constants.USER_INFO);
        if (StrUtil.isNotEmpty(userInfo)) {
            MDC.put("userInfo", userInfo);
        }

        try {
            chain.doFilter(servletRequest, response);
        } finally {
            MDC.clear();
        }


    }

    @Override
    public void destroy() {
        MDC.clear();
    }
}
