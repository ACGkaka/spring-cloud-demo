package com.example.demoauth.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p> @Title IndexController
 * <p> @Description 首页Controller
 *
 * @author ACGkaka
 * @date 2019/10/23 20:23
 */
@Controller
@AllArgsConstructor
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }


    @RequestMapping("/admin")
    @ResponseBody
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    @RequestMapping("/user")
    @ResponseBody
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }
}
