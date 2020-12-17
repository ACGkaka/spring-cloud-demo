package com.example.modules.mybatis.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * 用户表(SysUser)实体类
 *
 * @author ACGkaka
 * @since 2020-12-14 18:34:31
 */
@Data
@Accessors(chain = true)
public class SysUser implements Serializable {
    private static final long serialVersionUID = 360817734708089441L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 用户名
    */
    private String username;
    /**
    * 密码
    */
    private String password;

}