package com.example.auth.config;

import com.example.auth.security.service.ITDragonJwtAuthenticationEntryPoint;
import com.example.auth.security.service.ITDragonJwtAuthenticationTokenFilter;
import com.example.auth.security.service.MyLogoutSuccessHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p> @Title ITDragonWebSecurityConfig
 * <p> @Description 认证配置
 *
 * @author ACGkaka
 * @date 2020/12/22 9:52
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ITDragonWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private ITDragonJwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    private ITDragonJwtAuthenticationEntryPoint authenticationEntryPoint;

    private MyLogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager itdragonAuthenticationManager() throws Exception {
        return authenticationManager();
    }


    /**
     * 第一步：将JWT过滤器添加到默认的账号密码过滤器之前，表示token验证成功后无需登录
     * 第二步：配置异常处理器和登出处理器
     * 第三步：开启权限拦截，对所有请求进行拦截
     * 第四步：开放不需要拦截的请求，比如用户注册、OPTIONS请求和静态资源等
     * 第五步：允许OPTIONS请求，为跨域配置做准备
     * 第六步：允许访问静态资源，访问swagger时需要
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 添加jwt过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 配置未认证异常处理器
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
//                .and()
                // 设置登陆页
//                .formLogin().loginPage("/auth/login")
                // 配置登出逻辑
                .and().logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                // 开启权限拦截
                .and().authorizeRequests()
                // 开放不需要拦截的请求
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                // 允许所有OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 允许静态资源访问
                .antMatchers(HttpMethod.GET,
                        "/login",
                        "/favicon.ico",
                        "/js/**",
                        "/css/**"
                ).permitAll()
                // 对除了以上路径的所有请求进行权限拦截
                .anyRequest().authenticated()
                // 可以定制，除了以上路径，拦截哪些请求
//                .antMatchers("/itdragon/api/v1/**").authenticated()
                // 先暂时关闭跨站请求伪造，它限制除了get以外的大多数方法。
                .and().csrf().disable()
                // 允许跨域请求
                .cors().disable();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
    }

}