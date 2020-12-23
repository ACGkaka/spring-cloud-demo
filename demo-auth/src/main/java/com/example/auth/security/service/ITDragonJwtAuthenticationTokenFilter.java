package com.example.auth.security.service;

import com.example.auth.security.entity.JwtUser;
import com.example.auth.security.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p> @Title ITDragonJwtAuthenticationTokenFilter
 * <p> @Description 过滤器配置 - OncePerRequestFilter 顾名思义，它能够确保在一次请求中只通过一次filter，而不会重复执行，是由Spring提供的抽象类
 *
 * @author zhj
 * @date 2020/12/22 10:11
 */
@Slf4j
@Component
public class ITDragonJwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ITDragonJwtAuthenticationTokenFilter.class);

    @Value("${itdragon.jwt.header:Authorization}")
    private String tokenHeader;
    @Value("${itdragon.jwt.tokenHead:Bearer}")
    private String tokenHead;
    @Autowired
    private MyUserDetailsService userDetailsService;

    /**
     * 过滤器验证步骤
     * 第一步：从请求头中获取token
     * 第二步：从token中获取用户信息，判断token数据是否合法
     * 第三步：校验token是否有效，包括token是否过期、token是否已经刷新
     * 第四步：检验成功后将用户信息存放到SecurityContextHolder Context中
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 从请求头中获取token
        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            String authToken = authHeader.substring(tokenHead.length());
            // 从token中获取用户信息
            String username = JwtTokenUtil.getUsernameFromToken(authToken);
            if (Strings.isBlank(username)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Auth token is illegal");
                return;
            }
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                JwtUser tempUser = (JwtUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                LOGGER.info("SecurityContextHolder : {}", tempUser.getUsername());
            }

            // 验证token是否有效
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (JwtTokenUtil.validateToken(authToken, userDetails)) {
                // 将用户信息添加到 SecurityContextHolder 的 Context
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // 让目标资源执行，放行，进入过滤链的下一个过滤器
        filterChain.doFilter(request, response);

    }

}
