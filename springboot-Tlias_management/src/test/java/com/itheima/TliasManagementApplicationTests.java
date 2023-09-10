package com.itheima;

import com.itheima.pojo.Emp;
import com.itheima.service.DeptService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class TliasManagementApplicationTests {
    @Autowired
    private ApplicationContext context;//spring容器

    //获取bean对象
    @Test
    public void testGetBean(){
        //根据bean的名称获取
        Object deptService1 = context.getBean("deptServiceImpl");
        //根据bean的类型获取
        DeptService deptService2 = context.getBean(DeptService.class);
        //根据bean的名称及类型获取
        DeptService deptService3 = context.getBean("deptServiceImpl",DeptService.class);
        System.out.println("deptService1 = " + deptService1);
        System.out.println("deptService2 = " + deptService2);
        System.out.println("deptService3 = " + deptService3);
    }
    //bean的作用域
    @Test
    public void testScope(){

    }



    @Test
    void contextLoads() {
        String s = "11";
        System.out.println(s.substring(3));
    }

    /*
     * 生成JWT令牌
     * */
    @Test
    void testGenJWT() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","jack");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "itheima")//签名算法
                .setClaims(claims)//定义自定义的内容(载荷)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置有效期为1小时
                .compact();
        System.out.println(jwt);
    }
    /*
    * 解析jwt令牌
    * */
    @Test
    void testParseJWT(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiamFjayIsImlkIjoxLCJleHAiOjE2OTM4MzI5OTF9.jnB29iwhxTEdU3i9v4mxepijZfe7eiCB9OA43vpq4dM")
                .getBody();
        System.out.println(claims);
    }
}
