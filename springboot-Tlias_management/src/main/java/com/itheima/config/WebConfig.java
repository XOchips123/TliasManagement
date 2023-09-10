package com.itheima.config;

import com.itheima.interceptor.LoginInterceptor;
import com.itheima.interceptor.MyInterceptor01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 王添权
 * @Date: 2023/9/6 12:12
 * @Description:
 */
@Configuration//声明一个类是配置类
public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    private MyInterceptor01 myInterceptor;

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器,并拦截所有路径,此处/**表示所有路径
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login");
    }
}
