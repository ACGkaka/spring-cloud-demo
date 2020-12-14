package com.example.modules.mybatis.service;

import com.example.modules.mybatis.entity.SysUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 用户表(SysUser)表服务接口
 *
 * @author zhj
 * @since 2020-12-14 18:34:33
 */
public interface MybatisTestService {

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    ResponseEntity test1();

}