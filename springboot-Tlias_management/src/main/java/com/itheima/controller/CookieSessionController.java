package com.itheima.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class CookieSessionController {
    /**
     * request 是tomcat 对原始请求的一个封装:(有请求头,请求参数,等数据)
     * 提供了一些方法能够让我们获取数据
     * response 是tomcat 对原始响应的一个封装:
     * 提供了一些方法能够让我们存储数据,返回给浏览器
     */
    public void param(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getParameter("username");// 获取请求参数
        request.getHeader("User-Agent"); // 获取请求头
        request.getRequestURI();// 获取请求路径 /login
        request.getRequestURL();// 获取请求路径 // http://localhsot:3306/login
        // 原始方式响应json 字符串
        PrintWriter writer = response.getWriter();
        writer.write("{\"age\": 12345}");
        response.getOutputStream();// 响应流对象
    }

    // 登录
    //response 原始响应对象
    @GetMapping("/login1")
    public void login(String username, String password, HttpServletResponse response) {
        // 模拟用户登录
        if ("admin".equals(username)) { // 如果登录成功(模拟)
            Cookie cookie1 = new Cookie("username", "admin");
            response.addCookie(cookie1);// 把cookie 给浏览器
        }
    }

    // 认证(校验)
    @GetMapping("/data1")
    public void data(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();// key
            String value = cookie.getValue();
            if ("username".equals(name)) {
                if ("admin".equals(value)) {
                    System.out.println("用户登录 ,开始查询信息");
                    return;
                }

            }
        }
        System.out.println("用户未登录,请重新登录");
    }

    // 登录
    //response 原始响应对象
    @GetMapping("/login2")
    public void login2(String username, String password, HttpServletRequest request) {
        HttpSession session = request.getSession(); // 创建或者获取Session


        // 模拟用户登录
        if ("admin".equals(username)) { // 模拟查询成功
            session.setAttribute("username", "admin");
        }
    }

    // 认证(校验)
    @GetMapping("/data2")
    public void data2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object username = session.getAttribute("username");
        if (username != null) {
            System.out.println(username + " 登录过,请求数据开始");
            return;
        }
        System.out.println("没有登录过");

    }

}
