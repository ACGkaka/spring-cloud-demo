package com.example.modules.mybatis.service.impl;

import com.example.modules.mybatis.mapper.SysUserMapper;
import com.example.modules.mybatis.service.MybatisTestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author zhj
 * @since 2020-12-14 18:34:33
 */
@Service
@AllArgsConstructor
public class MybatisTestServiceImpl implements MybatisTestService {

    private SysUserMapper sysUserMapper;

    @Override
    public ResponseEntity test1() {
        return ResponseEntity.ok(sysUserMapper.queryAll());
    }

}