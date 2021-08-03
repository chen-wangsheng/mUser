package com.chinasoft.user.service;

import com.chinasoft.common.utils.Result;
import com.chinasoft.user.entity.dto.UserInfoDTO;
import com.chinasoft.user.entity.vo.RegisterVO;
import com.chinasoft.user.entity.vo.UserQueryVO;
import com.chinasoft.user.entity.vo.UserUpdateVO;

import java.util.Date;

public interface UserService {

    /**
     * 删除用户信息
     * @param userId
     * @return
     */
    Result delUserInfo(Integer userId);

    /**
     * 查询用户详情
     * @param userId
     * @return
     */
    Result getUserInfo(Integer userId);

    /**
     * 修改用户信息
     * @param userUpdateVO
     * @return
     */
    Result updateUserInfo(UserUpdateVO userUpdateVO);

    /**
     * 查询用户列表
     * @param pageNum
     * @param pageSize
     * @param userQueryVO
     * @return result
     */
    Result getUserPageList(Integer pageNum, Integer pageSize, UserQueryVO userQueryVO);

    /**
     * 根据手机号查询用户信息
     * @param mobile
     * @return
     */
    UserInfoDTO getUserLoginInfo(String mobile);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 用户注册
     * @param registerVO
     */
    void regist(RegisterVO registerVO);

    /**
     * 修改设备信息
     * @param mobile 手机号
     * @param loginDevice 登录设备
     * @param ipAddr ip
     * @param lastFailedTimes 累计失败次数
     */
    void updateDeviceInfo(String mobile, String loginDevice, String ipAddr, Date date, int lastFailedTimes);

    void updateById(Integer userId);

    /**
     * 查询用户错误次数
     * @param mobile
     * @return
     */
    Long getFailedTime(String mobile);

    /**
     * 失败5次锁定账号
     * @param mobile
     */
    void updateStatus(String mobile, Integer times);

}
