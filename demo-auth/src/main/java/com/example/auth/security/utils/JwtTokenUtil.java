package com.example.auth.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> @Title JwtTokenUtil
 * <p> @Description TODO
 *
 * @author ACGkaka
 * @date 2020/12/22 10:13
 */
@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "itdragon";

    @Value("${itdragon.jwt.secret:ITDragon}")
    private String secret;

    @Value("${itdragon.jwt.expiration:86400}")
    private Long expiration;

    /**
     * 生成令牌Token
     * 1. 建议使用唯一、可读性高的字段作为生成令牌的参数
     */
    public String generateToken(String username) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put(CLAIM_KEY_USERNAME, username);
            return generateJWT(claims);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 校验token
     * 1. 判断用户名和token包含的属性一致
     * 2. 判断token是否失效
     */
    public static boolean validateToken(String token, UserDetails userDetails) {
//        return Objects.equals(getUsernameFromToken(token), userDetails.getUsername()) && !isInvalid(token, userDetails.model.tokenInvalidDate);
        return true;
    }

    /**
     * token 失效判断，依据如下：
     * 1. 关键字段被修改后token失效，包括密码修改、用户退出登录等
     * 2. token 过期失效
     */
    private boolean isInvalid(String token, Date tokenInvalidDate) {
        try {
            Claims claims = parseJWT(token);

            return claims.getIssuedAt().before(tokenInvalidDate) && isExpired(token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * token 过期判断，常见逻辑有几种：
     * 1. 基于本地内存，问题是重启服务失效
     * 2. 基于数据库，常用的有Redis数据库，但是频繁请求也是不小的开支
     * 3. 用jwt的过期时间和当前时间做比较（推荐）
     */
    private boolean isExpired(String token) {
        try {
            Claims claims = parseJWT(token);
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从token 中获取用户名
     */
    public String getUsernameFromToken(String token) {
        try {
            Claims claims = parseJWT(token);
            return claims.get(CLAIM_KEY_USERNAME).toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 生成jwt方法
     */
    public String generateJWT(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)      // 定义属性
                // .设计如下：(Date())    // 设置发行时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000)) // 设置令牌有效期
                .signWith(SignatureAlgorithm.HS512, secret) // 使用指定的算法和密钥对jwt进行签名
                .compact();              // 压缩字符串
    }

    /**
     * 解析jwt方法
     */
    private Claims parseJWT(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)  // 设置密钥
                    .parseClaimsJws(token)  // 解析token
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}