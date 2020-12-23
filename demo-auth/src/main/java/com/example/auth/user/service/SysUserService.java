package com.example.auth.user.service;

import com.example.auth.user.dao.SysUserMapper;
import com.example.auth.user.entity.SysUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p> @Title SysUserService
 * <p> @Description 系统用户Service
 *
 * @author ACGkaka
 * @date 2019/11/23 10:53
 */
@Service
@AllArgsConstructor
public class SysUserService {

    private SysUserMapper sysUserMapper;

    public SysUser findByUsername(String username) {
        return sysUserMapper.findByUsername(username);
    }
}
