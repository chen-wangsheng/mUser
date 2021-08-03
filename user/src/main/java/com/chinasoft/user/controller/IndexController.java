package com.chinasoft.user.controller;

import com.chinasoft.common.exception.CommonException;
import com.chinasoft.common.jwt.JwtUtils;
import com.chinasoft.common.utils.DeviceUtil;
import com.chinasoft.common.utils.IPUtils;
import com.chinasoft.common.utils.Result;
import com.chinasoft.user.entity.User;
import com.chinasoft.user.entity.dto.UserInfoDTO;
import com.chinasoft.user.entity.vo.LoginVO;
import com.chinasoft.user.entity.vo.RegisterVO;
import com.chinasoft.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public Result login(@RequestBody LoginVO loginVO) {
        String token = userService.login(loginVO.getMobile(), loginVO.getPwd());
        return new Result().success("token", token);
    }

    @ApiOperation("用户登录信息")
    @GetMapping("/info")
    public Result info(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UserInfoDTO userLoginInfo = userService.getUserLoginInfo(memberId);
        /*
        Map<String, Object> result = new HashMap<>();
        result.put("roles", "[admin]");
        result.put("name", memberId);
        result.put("avatar","http://t14.baidu.com/it/u=119284402,3289437878&fm=224&app=112&f=JPEG?w=500&h=500&s=59243D720D1073C412C891C70000B0A1");
        */
        return new Result().success("userInfo", userLoginInfo);
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("/getLoginInfo")
    public Result getLoginInfo(HttpServletRequest request, Device device){
        try {
            String mobile = JwtUtils.getMemberIdByJwtToken(request);
            UserInfoDTO userLoginInfo = userService.getUserLoginInfo(mobile);
            String loginDevice = DeviceUtil.getdevice(device);
            String ipAddr = IPUtils.getIpAddr(request);
            userLoginInfo.setLastLoginEqpt(loginDevice);
            userLoginInfo.setLastLoginIp(ipAddr);
            userService.updateDeviceInfo(mobile, loginDevice, ipAddr, new Date(), 0);

            return Result.ok().success("userInfo", userLoginInfo);
        }catch (Exception e){
            e.printStackTrace();
            throw new CommonException(20001, "获取token失败！");
        }
    }

    @ApiOperation("用户注册接口")
    @PostMapping("/regist")
    public Result regist(@RequestBody RegisterVO registerVO) {
        userService.regist(registerVO);
        return Result.ok();
    }

    @ApiOperation("用户注销接口")
    @PutMapping("/logout/{userId}")
    @ApiParam(name = "userId", value = "用户id", required = true)
    public Result logout(@PathVariable("userId") Integer userId) {
        userService.updateById(userId);
        return Result.ok();
    }
}
