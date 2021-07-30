package com.chinasoft.user.controller;

import com.chinasoft.common.dto.UserDTO;
import com.chinasoft.common.utils.Result;
import com.chinasoft.common.vo.UserUpdateVO;
import com.chinasoft.user.entity.User;
import com.chinasoft.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: VanceChen
 * @Date: 2021/7/29 18:11
 * @Description: TODO
 **/
@RestController
@RequestMapping("/user")
@Api(value = "⽤户模块接⼝", tags = "⽤户管理")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("删除用户信息")
    @ApiParam(name = "userId", value = "用户id", required = true)
    @DeleteMapping("/delUserInfo/{userId}")
    public Result delUserInfo(@PathVariable("userId") Integer userId) {
        Result result = userService.delUserInfo(userId);
        return result;
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/updateUserInfo")
    public Result updateUserInfo(@RequestBody UserUpdateVO userUpdateVO) {
        return userService.updateUserInfo(userUpdateVO);
    }

    @ApiOperation("查询用户详情")
    @ApiParam(name = "userId", value = "用户id", required = true)
    @GetMapping("/getUserInfo/{userId}")
    public Result getUserInfo(@PathVariable("userId") Integer userId) {
        return userService.getUserInfo(userId);
    }

    @ApiOperation("查询用户列表")
    @GetMapping("/getUserList")
    public Result getUserList() {
        List<UserDTO> list = userService.getUserList();
        return Result.ok().data("items", list);
    }
}