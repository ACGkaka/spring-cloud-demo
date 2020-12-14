package com.example.modules.hystrix.controller;

import com.example.modules.hystrix.service.HystrixTestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p> @Title HystrixTestController
 * <p> @Description 测试 Hystrix 的 服务隔离 保护措施。
 *
 * @author ACGkaka
 * @date 2020/12/14 15:36
 */
@Controller
@RequestMapping("/hystrix")
@AllArgsConstructor
public class HystrixTestController {

    private HystrixTestService hystrixTestService;

    @RequestMapping("/test1")
    @ResponseBody
    public String test1() {
        return hystrixTestService.test1();
    }
}
