package com.example.modules.ribbon.controller;

import com.example.modules.ribbon.service.RibbonTestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p> @Title RibbonTestController
 * <p> @Description 测试 Ribbon 的轮询策略
 *
 * @author ACGkaka
 * @date 2020/12/14 14:44
 */
@Controller
@RequestMapping("/ribbon")
@AllArgsConstructor
public class RibbonTestController {

    private RibbonTestService ribbonTestService;

    @GetMapping("/test1")
    @ResponseBody
    public String test1() {
        return ribbonTestService.test1();
    }
}
