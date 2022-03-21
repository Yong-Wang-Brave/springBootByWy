package com.wy.demo.过滤器拦截器监听器.FilterRegistrationBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Test2Filter implements Filter {

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
       HttpServletRequest request = (HttpServletRequest)arg0;
        System.out.println("自动以过滤器filter2触发，拦截url"+request.getRequestURI());
        arg2.doFilter(arg0,arg1);
    }
}
