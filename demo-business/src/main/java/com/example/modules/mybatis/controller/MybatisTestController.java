package com.example.modules.mybatis.controller;

import com.example.modules.mybatis.service.MybatisTestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户表(SysUser)表控制层
 *
 * @author zhj
 * @since 2020-12-14 18:34:33
 */
@RestController
@RequestMapping("/mybatis")
@AllArgsConstructor
public class MybatisTestController {

    private MybatisTestService mybatisTestService;

    /**
     * 查询所有数据
     *
     * @return 单条数据
     */
    @GetMapping("/test1")
    @ResponseBody
    public ResponseEntity test1() {
        return this.mybatisTestService.test1();
    }

}