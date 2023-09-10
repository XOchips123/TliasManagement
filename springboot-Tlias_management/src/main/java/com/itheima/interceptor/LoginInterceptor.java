package com.itheima.interceptor;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 王添权
 * @Date: 2023/9/6 15:08
 * @Description:
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 4.1 获取 请求token
        String token = request.getHeader("Token");//返回HTTP请求头中名为'Token'的值
        // 判断token 是否为空
        if(token!=null&& !token.isEmpty()){
            try {
                // 如果不为空 解析token
                Claims claims = JwtUtils.parseJWT(token);
                // 如果解析成功, 放行
                return true;
            } catch (Exception e) {
                // 解析失败
            }
        }
        // token 为空
        // 响应失败消息
        Result error = Result.error("NOT_LOGIN");
        String jsonString = JSON.toJSONString(error);
        response.getWriter().write(jsonString);

        return false;
    }
}
