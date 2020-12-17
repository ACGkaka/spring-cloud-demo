package com.example.modules.hystrix.service.impl;

import com.example.modules.hystrix.service.HystrixTestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

/**
 * <p> @Title HystrixTestServiceImpl
 * <p> @Description 测试 Hystrix 的 服务熔断、服务隔离 保护措施。
 *
 * @author ACGkaka
 * @date 2020/12/14 15:39
 */
@Service
public class HystrixTestServiceImpl implements HystrixTestService {

    @Override
    @HystrixCommand(fallbackMethod = "fallback", ignoreExceptions = {InvalidParameterException.class})
    public String test1() {
        System.out.println("线程池名称" + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello World.";
    }

    /**
     * 服务无法访问的情况下的默认返回数据
     * @return 数据
     */
    private String fallback() {
        return "服务器繁忙";
    }
}
