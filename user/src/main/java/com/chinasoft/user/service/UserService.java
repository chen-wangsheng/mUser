package com.chinasoft.user.service;

import com.chinasoft.common.dto.UserDTO;
import com.chinasoft.common.utils.Result;
import com.chinasoft.common.vo.UserUpdateVO;

import java.util.List;

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
     * @return
     */
    List<UserDTO> getUserList();
}
