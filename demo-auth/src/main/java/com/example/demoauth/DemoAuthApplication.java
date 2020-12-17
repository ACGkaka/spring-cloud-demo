package com.example.demoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DemoAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAuthApplication.class, args);
    }

}
