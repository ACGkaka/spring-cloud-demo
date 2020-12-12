package com.example.modules.welcome;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p> @Title WelcomeController
 * <p> @Description 首页
 *
 * @author zhj
 * @date 2020/12/11 15:52
 */
@Slf4j
@Controller
public class WelcomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);

    @RequestMapping("/business")
    @ResponseBody
    public String welcome() {
        LOGGER.info("有一个用户访问了系统。");
        return "Hello World";
    }
}
