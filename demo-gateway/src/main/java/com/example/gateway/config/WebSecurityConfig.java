package com.example.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;

/**
 * Spring Gateway 是基于WebFlux的，底层使用netty实现的，和Spring Boot Web不兼容，
 * 这个类是用于配置基于WebFlux的Spring Security 的相关配置的。
 *
 * @author ACGkaka
 */
@EnableWebFluxSecurity
public class WebSecurityConfig {


    /**
     * security的鉴权排除的url列表
     */
    private static final String[] EXCLUDED_AUTH_PAGES = {
            "/auth/login",
            "/auth/js/**",
            "/auth/css/**"
    };

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                // 无需进行权限过滤的请求路径
                .pathMatchers(EXCLUDED_AUTH_PAGES).permitAll()
                // option 请求默认放行
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated()
                .and()
                .httpBasic()
                .and()
                // 启动页面表单登陆,spring security 内置了一个登陆页面/login
                .formLogin().loginPage("/auth/login")
                .and().csrf().disable()//必须支持跨域
                .logout().disable();

        return http.build();
    }
}
