package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: 王添权
 * @Date: 2023/9/6 15:14
 * @Description: 全局异常处理器
 */
@RestControllerAdvice//= @ControllerAdvice + @ResponseBody 类上声明一个异常处理器
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//方法上,表示捕获哪类异常
    public Result handException(Exception e){
        //模拟异常的处理:记录日志等
        e.printStackTrace();
        return Result.error("系统异常,请联系管理员");
    }
    @ExceptionHandler(RuntimeException.class)//方法上,表示捕获哪类异常
    public Result handException(RuntimeException e){
        //模拟异常的处理:记录日志等
        e.printStackTrace();
        return Result.error("系统异常,请联系管理员");
    }
}
