package com.wy.demo.filter.headToSession;

import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
/**  过滤器的实现   过滤器的实现  过滤器的实现  过滤器的实现
 * 要实现从session中获取参数，处理后，放到session中
 * 1 继承OncePerRequestFilter，获取请求头的内容，放入session,放入拦截器链
 * 2 config 内容应该没用
 * 3 通过@SessionAttribute 获取session中的值
 */
public class filterAddToSession extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //从request中获取请求头
        String wy = httpServletRequest.getHeader("wy");
        //根据wy进行相关处理然后存储到session
        httpServletRequest.getSession().setAttribute("wy",wy+"888");
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
