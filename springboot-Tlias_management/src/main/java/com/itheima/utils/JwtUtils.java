package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "itheima";
    private static Long expire = 43200000L; // 12H

    /**
     * 令牌:
     * 第一部分: (头) 存了  HS256加密方式
     * 第二部分:(payload,载荷) 存储了是内容
     * 第三部分:  (签名) 是前面两部分 使用第一部分指定的加密方式,结合密钥生成的一个加密字符串  签名 = 头+载荷+密钥
     * 令牌校验时:使用相同的算法和相同的密钥再次计算签名,如果计算出来的和令牌中的一样,令牌合法
     * 令牌的安全性体现在不可伪造(唯一),内容是可以被前端看到的(要求令牌中不要存储敏感信息,例如密码)
     * 防止令牌被截获造成的信息泄露,令牌设置一个有效期,防止信息的过度泄露
     * 前两部分存储都是使用了一种base64 的编码方式 : 使用64 个字符 A-Za-z0-9 + _ (= 是补位)
     * eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ6aGFuZ3NhbiJ9.34eXZgI81MJPpyKbuOiknahbx2iZKzJa26_fOQo1EmE
     */
    public static String generateJwt(Map<String, Object> data) {

        JwtBuilder builder = Jwts.builder();// 生成一个构建者对象
        builder.signWith(SignatureAlgorithm.HS256, signKey);//signKey 至少4位
        builder.addClaims(data);// 存储数据
        builder.setExpiration(new Date(System.currentTimeMillis() + expire));
        String jwt = builder.compact();// 生成令牌

/*        链式编程
        String jwt = Jwts.builder()//生成一个构建者对象
                .addClaims(claims)//存储数据
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();//生成令牌*/
        return jwt;
    }

    /**
     * 解析JWT令牌
     * 注意:令牌解析失败,会抛出异常,需要使用者捕获
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt) throws Exception {
        Claims claims = Jwts.parser()//获取解析器
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
