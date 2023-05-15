package com.wy.demo.filter;

import cn.hutool.core.util.StrUtil;
import com.wy.demo.config.feign.Constants;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(servletRequest);
        String reqId = servletRequest.getHeader(Constants.REQUEST_ID);
        if (StrUtil.isEmpty(reqId)) {
            RandomStringUtils.random(16, Constants.SEEDS);
            requestWrapper.addHeader(Constants.REQUEST_ID, reqId);
        }
        MDC.put(Constants.REQUEST_ID, reqId);

        try {
            chain.doFilter(requestWrapper, response);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {
        MDC.remove(Constants.REQUEST_ID);
    }
}
