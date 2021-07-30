package com.chinasoft.user.controller;

import com.chinasoft.common.utils.Result;
import com.chinasoft.user.entity.User;
import com.chinasoft.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: VanceChen
 * @Date: 2021/7/30 11:05
 * @Description: 首页
 **/
@RestController
@RequestMapping("/index")
@Api(value = "首页接⼝", tags = "首页管理")
@CrossOrigin
public class IndexController {

    @Autowired
    private UserService userService;


    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    @ApiOperation("用户登录信息")
    @GetMapping("/info")
    public Result info() {
        return Result.ok().data("roles", "[admin]").data("name","admin").data("avatar","http://t14.baidu.com/it/u=119284402,3289437878&fm=224&app=112&f=JPEG?w=500&h=500&s=59243D720D1073C412C891C70000B0A1");
    }

    @ApiOperation("用户注册接口")
    @PostMapping("/regist")
    public Result regist(@RequestBody User user) {
        return Result.ok().data("token", null);
    }

    @ApiOperation("用户注销接口")
    @PostMapping("/logout")
    public Result logout(@RequestBody User user) {
        return Result.ok().data("token", null);
    }
}
