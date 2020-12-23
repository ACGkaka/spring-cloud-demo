package com.example.auth.controller;

import com.example.auth.security.service.AuthService;
import com.example.auth.security.vo.JwtRequestVO;
import com.example.auth.security.vo.JwtResponseVO;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    private AuthService authService;

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

//    @PostMapping("/login")
//    public ResponseEntity createAuthenticationToken(String username, String password) {
//        String token = authService.login(username, password);
//
//        return ResponseEntity.ok(new JwtResponseVO(token));
//    }
    @PostMapping("/login")
    public void createAuthenticationToken(String username, String password) {
        authService.login(username, password);
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
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
