package com.chinasoft.user.service;

import com.chinasoft.common.utils.Result;
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
}
