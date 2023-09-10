package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 王添权
 * @Date: 2023/9/4 16:43
 * @Description:
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result Login(@RequestBody Emp emp){
        //登录的本质就是查询,根据用户名密码查询
        log.info("员工登录:{},{}",emp.getUsername(),emp.getPassword());
        Emp e =empService.Login(emp);

        //登录成功,生成令牌,返回令牌
        if(e!=null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        //登陆失败,返回错误信息
        return Result.error("用户名或密码错误");
    }
}
