package com.example.auth.security.service;

import com.example.auth.security.utils.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p> @Title ITDragonAuthService
 * <p> @Description 登录Service
 *
 * @author ACGkaka
 * @date 2020/12/22 17:42
 */
@Service
@AllArgsConstructor
public class AuthService {

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private JwtTokenUtil jwtTokenUtil;

    /**
     * 处理登录请求
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    public String login(String username, String password) {
        // 初始化UsernamePasswordAuthenticationToken对象
        UsernamePasswordAuthenticationToken upAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        // 身份验证
        Authentication authentication = authenticationManager.authenticate(upAuthenticationToken);
        // 验证成功后回将用户信息存放到 securityContextHolder的Context中
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成token并返回
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails.getUsername());
    }

}
