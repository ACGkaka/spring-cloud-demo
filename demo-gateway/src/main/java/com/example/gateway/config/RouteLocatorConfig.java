package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> @Title RouteLocatorConfig
 * <p> @Description RouteLocator 配置类
 *
 * @author ACGkaka
 * @date 2020/12/17 10:54
 */
@Configuration
public class RouteLocatorConfig {

    /**
     * 手动实现请求路径的重写，将localhost:1003 -> lb://demo-business
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .predicate(exchange -> exchange.getRequest().getPath().subPath(0).toString().equals(("/")))
                        .filters(f -> f.rewritePath("/", "/index"))
                        .uri("lb://demo-business"))
                .build();
    }
}
