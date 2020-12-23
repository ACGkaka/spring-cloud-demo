package com.example.auth.user.entity;

import lombok.Data;

import java.util.Date;

/**
 * <p> @Title SysUser
 * <p> @Description 系统用户
 *
 * @author ACGkaka
 * @date 2020/12/22 14:32
 */
@Data
public class SysUser {

    /** 主键 */
    private Long id;

    /** 用户名. */
    private String username;

    /** 密码. */
    private String password;

    /** 是否有效 */
    private boolean valid;

    /** 失效时间 */
    private Date tokenInvalidDate;

}