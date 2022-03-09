package com.wy.demo.过滤器拦截器监听器;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

@Log4j2
//@Component
/**   博客地址：https://mp.weixin.qq.com/s?__biz=MzA4NzQ0Njc4Ng==&mid=2247503650&idx=2&sn=747553dcbede948d63d3cf7d13cdf52b&chksm=903bcb4fa74c42591afb1b683680947f106dbb8f05eb20f96e4aedc37453346280ff708ba202&scene=178&cur_album_id=1909106321895784453#rd
 * 如同它的名字一样，过滤器是处于客户端和服务器资源文件之间的一道过滤网，
 * 帮助我们过滤掉一些不符合要求的请求，
 * 通常用作 Session 校验，判断用户权限，
 * 如果不符合设定条件，则会被拦截到特殊的地址或者基于特殊的响应。
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("服务器启动的时候，初始化过滤器");
    }
  
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
        String requestUri = request.getRequestURI();
        log.info("请求地址是："+requestUri);
      /*  if (requestUri.contains("/addSession")
            || requestUri.contains("/removeSession")
            || requestUri.contains("/online")
            || requestUri.contains("/favicon.ico")) {
            filterChain.doFilter(servletRequest, response);
        } else {
            wrapper.sendRedirect("/online");
        }*/
        filterChain.doFilter(servletRequest, response);
    }
  
    @Override
    public void destroy() {
        //在服务关闭时销毁
        log.info("关闭服务器的时候，销毁过滤器");
    }
}