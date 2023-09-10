package com.itheima.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 王添权
 * @Date: 2023/9/6 12:03
 * @Description:
 */
//@Component
public class MyInterceptor01 implements HandlerInterceptor {
    /*
     * 前置拦截
     * return true 放行,false 拒绝
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("前置拦截");
        return true;
    }

    /**
     * 后置拦截: 目标方法执行后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("后置拦截");
    }

    /**
     * 后置拦截: 如果有多个拦截器,所有拦截器 的 前置和后置方法执行后 才执行的方法
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion后置拦截");
    }
}
