package com.itheima.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author: 王添权
 * @Date: 2023/9/9 09:19
 * @Description:
 */
@Component
@Aspect
public class homework {
    @Around("execution(* com.itheima.controller.*Controller.*(..))")
    public Object mod(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("运行时间为:"+(end-start)+"ms");
        return result;
    }
}
