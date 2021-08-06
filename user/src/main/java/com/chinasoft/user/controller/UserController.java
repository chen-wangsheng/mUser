package com.chinasoft.user.controller;

import com.alibaba.excel.EasyExcel;
import com.chinasoft.common.utils.Result;
import com.chinasoft.user.entity.dto.UserData;
import com.chinasoft.user.entity.dto.UserInfoDTO;
import com.chinasoft.user.entity.vo.UserQueryVO;
import com.chinasoft.user.entity.vo.UserUpdateVO;
import com.chinasoft.user.listener.UserExcelListener;
import com.chinasoft.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true,dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页数", required = true,dataType="int", defaultValue = "10")
    })
    @PostMapping("/getUserPageList/{pageNum}/{pageSize}")
    public Result getUserPageList(
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("pageSize") Integer pageSize,
            @RequestBody UserQueryVO userQueryVO) {
        Result list = userService.getUserPageList(pageNum, pageSize, userQueryVO);
        return list;
    }

    @ApiOperation("获取用户登录信息")
    @ApiParam(name = "mobile", value = "用户id", required = true)
    @GetMapping("/getUserLoginInfo/{mobile}")
    public Result getUserLoginInfo(@PathVariable("mobile") String mobile) {
        UserInfoDTO userLoginInfo = userService.getUserLoginInfo(mobile);
        return new Result().success("userInfo",userLoginInfo);
    }

    @ApiOperation("修改用户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true,dataType = "String"),
            @ApiImplicitParam(name = "times", value = "次数", required = true,dataType="int")
    })
    @PutMapping("/updateStatus/{mobile}/{times}")
    public Result updateStatus(@PathVariable("mobile") String mobile,@PathVariable("times") Integer times) {
        userService.updateStatus(mobile, times);
        return Result.ok();
    }

    @ApiOperation("上传头像")
    @ApiParam(name = "file", value = "文件", required = true)
    @PostMapping("/fileUpload")
    public Result fileUpload(MultipartFile file) {
        String upload = userService.fileUpload(file);
        return new Result().success("图片路径", upload);
    }

    @ApiOperation("解冻用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true,dataType = "String"),
            @ApiImplicitParam(name = "status", value = "次数", required = true,dataType="String")
    })
    @PutMapping("/updateFrozen/{id}/{status}")
    public Result updateFrozen(@PathVariable("id") Integer id,@PathVariable("status") String status) {
        userService.updateFrozen(id, status);
        return Result.ok();
    }

    @ApiOperation(value = "Excel批量导入用户信息")
    @ApiParam(name = "file", value = "文件", required = true)
    @PostMapping("/importUser")
    public Result importUser(MultipartFile file) {
        try {
            //1 获取文件输入流
            InputStream inputStream = file.getInputStream();

            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(inputStream, UserData.class, new UserExcelListener(userService)).sheet().doRead();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Result.error().message("添加失败");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error().message("文件错误");
        }
        return Result.ok();
    }
}
