package com.itheima.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Result;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 王添权
 * @Date: 2023/9/6 10:23
 * @Description:
 */

//@WebFilter(urlPatterns = "/*")
//@WebFilter(urlPatterns = "/login")//只能拦截login页面,路径为login/1之类的就不会拦截
/*
 *  /* 表示拦截所有领
 *  /login  精确匹配
 *  /emps/*  半匹配
 *  过滤器链:
 *      注解方式配置的多个过滤器,执行顺序是按照类名字(字符串)的自然排序
 */
public class Filter01 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器1初始化方法...");
        //用来初始化资源 一般不用
        // 服务器启动时执行
    }

    /**
     * servletRequest 原始请求对象,真正的对象事HttpServletRequest,此处使用的是接口接收的
     * servletResponse 原始响应对象,真正的对象是HttpServletResponse
     * filterChain 是一个内部的参数 doFilter 放行
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强制类型转换:目的是为了使用子类独有的方法
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String remoteHost = request.getRemoteHost();
        System.out.println(remoteHost);


        //模拟登录失败
        //手动转JSON
        Result error = Result.error("NOT_LOGIN");
        String jsonString = JSON.toJSONString(error);
        response.getWriter().write(jsonString);



/*        System.out.println("doFilter1 过滤方法,每次请求都执行.....");
        System.out.println("1放行前逻辑");
        filterChain.doFilter(servletRequest, servletResponse); 放行
        System.out.println("1放行后逻辑");*/
    }

    @Override
    public void destroy() {
        System.out.println("过滤器1销毁方法...");
        //用来销毁资源 一般不用
        //服务器正常停止执行
    }
}
