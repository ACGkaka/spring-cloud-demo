package com.example.auth.security.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * <p> @Title ITDragonJwtAuthenticationEntryPoint
 * <p> @Description 处理没有登录的请求
 *
 * @author zhj
 * @date 2020/12/22 10:49
 */
@Component
public class ITDragonJwtAuthenticationEntryPoint implements AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        if(isAjaxRequest(request)){
            // 同时判断是否ajax请求，是ajax请求则返回json
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, String.format("401 身份验证失败: %s", authException.getMessage()));
        }else{
            // 否则跳转失败页面
            response.sendRedirect("/auth/login");
        }

    }

    /**
     * 同时判断是否ajax请求
     *
     * @param request 请求
     * @return 是否为 Ajax 请求
     */
    private static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return Objects.nonNull(ajaxFlag) && "XMLHttpRequest".equals(ajaxFlag);
    }
}
