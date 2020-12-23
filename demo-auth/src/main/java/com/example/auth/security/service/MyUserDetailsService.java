package com.example.auth.security.service;

import com.example.auth.security.entity.JwtUser;
import com.example.auth.user.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p> @Title ITDragonUserDetailsService
 * <p> @Description 系统用户认证配置
 *
 * @author ACGkaka
 * @date 2020/12/22 13:50
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户登录: {}", username);
        // todo ... something
        return new JwtUser(sysUserService.findByUsername(username));
    }

}