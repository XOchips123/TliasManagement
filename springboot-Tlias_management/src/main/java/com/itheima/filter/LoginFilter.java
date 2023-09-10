package com.itheima.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 王添权
 * @Date: 2023/9/6 11:18
 * @Description:
 */
//@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强转对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取请求路径
        String requestURI = request.getRequestURI();//"/Login"
        /*StringBuffer requestURL = request.getRequestURL();
        String url = requestURL.toString();
        if(url.contains("/login"))URI 统一资源标识符  URL 统一资源定位符*/
        //判断是否是登录请求
        if("/login".equals(requestURI)){
            //如果是,则放行
            filterChain.doFilter(request,response);
            return;
        }
        //如果不是,获取请求Token
        String token = request.getHeader("Token");
        //判断token是否为空
        if(token!=null&& !token.isEmpty()){
            try {//不为空,解析token
                JwtUtils.parseJWT(token);
                filterChain.doFilter(request,response);
                return;
            } catch (Exception e) {
                //解析失败
                throw new RuntimeException(e);
            }
        }
        //token为空,响应失败消息
        Result error = Result.error("NOT_LOGIN");
        String jsonString = JSON.toJSONString(error);//引入fastjson依赖
        response.getWriter().write(jsonString);
    }
}
