package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: 王添权
 * @Date: 2023/9/6 10:56
 * @Description:
 */
//@WebFilter("/login/*")
public class Filter02 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器2初始化方法...");

    }
    @Override
    public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain Chain) throws IOException, ServletException {
        System.out.println("doFilter2 过滤方法,每次请求都执行.....");
        System.out.println("2放行前逻辑");
        Chain.doFilter(Request, Response);// 放行
        System.out.println("2放行后逻辑");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器2销毁方法...");
    }
}
