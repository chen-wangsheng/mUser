package com.chinasoft.user.service;

import com.chinasoft.common.utils.Result;
import com.chinasoft.user.entity.dto.UserInfoDTO;
import com.chinasoft.user.entity.vo.RegisterVO;
import com.chinasoft.user.entity.vo.UserQueryVO;
import com.chinasoft.user.entity.vo.UserUpdateVO;

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
}
