package com.example.auth.security.vo;

import lombok.Data;

/**
 * <p> @Title JwtRequestVO
 * <p> @Description 登录请求体
 *
 * @author ACGkaka
 * @date 2020/12/22 17:38
 */
@Data
public class JwtRequestVO {

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;
}
