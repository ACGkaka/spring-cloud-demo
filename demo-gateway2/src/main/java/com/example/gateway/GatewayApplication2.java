package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务网关 - Gateway
 *
 * @author ACGkaka
 */
@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication2 {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication2.class, args);
    }

}
