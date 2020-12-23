package com.example.auth.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p> @Title JwtResponseVO
 * <p> @Description 登录响应体
 *
 * @author ACGkaka
 * @date 2020/12/22 17:40
 */
@Data
@AllArgsConstructor
public class JwtResponseVO {

    /** Token */
    private String token;
}
