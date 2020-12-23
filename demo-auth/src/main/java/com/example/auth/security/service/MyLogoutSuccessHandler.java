package com.example.auth.security.service;

import com.example.auth.security.entity.JwtUser;
import com.example.auth.user.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p> @Title ITDragonLogoutSuccessHandler
 * <p> @Description 退出登录成功处理器
 *
 * @author ACGkaka
 * @date 2020/12/22 10:51
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            // 更新用户登出时间，使其旧token失效
            if (authentication != null) {
                JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
                SysUser user = jwtUser.getUser();
                user.setTokenInvalidDate(new Date());
//                userRepository.save(user);
            }

            // 方式一：返回登录页面
            response.sendRedirect("/auth/login");

            /// 方式二：返回json格式数据
//            response.setContentType("application/json;charset=utf-8");
//            PrintWriter out = response.getWriter();
//            Map<String, Object> map = new HashMap<>();
//            map.put("code", 200);
//            map.put("message", "登出成功");
//            out.write(JSONObject.toJSONString(map));
//            out.flush();
//            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
