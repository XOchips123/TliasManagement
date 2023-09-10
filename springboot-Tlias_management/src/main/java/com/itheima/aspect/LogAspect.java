package com.itheima.aspect;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Author: 王添权
 * @Date: 2023/9/8 16:06
 * @Description:
 */
@Component
@Aspect
public class LogAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;


    @Around("@annotation(com.itheima.anno.Log)")
    public Object logRecord(ProceedingJoinPoint pjp) throws Throwable {
        OperateLog operateLog = new OperateLog();
        //获取用户id
        String token = request.getHeader("Token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer userId = (Integer) claims.get("id");
        operateLog.setOperateUser(userId);//操作人
        operateLog.setOperateTime(LocalDateTime.now());//日期
        operateLog.setClassName(pjp.getTarget().getClass().getName());//获取类名
        operateLog.setMethodName(pjp.getSignature().getName());//获取方法名
        operateLog.setMethodParams(Arrays.toString(pjp.getArgs()));//获取方法参数
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = pjp.proceed();
            long end = System.currentTimeMillis();
            operateLog.setCostTime(end - start);//获取运行时间
            operateLog.setReturnValue(result.toString());//返回值
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;//必须把捕获到的异常抛出,否则异常处理器捕获不到
        } finally {
            operateLogMapper.insert(operateLog);
        }
        return result;
    }

}
