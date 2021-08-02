package com.chinasoft.user.service.impl;

import com.chinasoft.common.utils.Result;
import com.chinasoft.user.dao.UserDao;
import com.chinasoft.user.entity.User;
import com.chinasoft.user.entity.dto.UserDTO;
import com.chinasoft.user.entity.vo.UserQueryVO;
import com.chinasoft.user.entity.vo.UserUpdateVO;
import com.chinasoft.user.service.UserService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: VanceChen
 * @Date: 2021/7/29 18:12
 * @Description: TODO
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 删除用户信息
     * @param userId
     * @return
     */
    @Override
    public Result delUserInfo(Integer userId) {
        int i = userDao.deleteByPrimaryKey(userId);
        if(i <= 0){
            return Result.error().message("删除用户失败！");
        }
        return Result.ok();
    }

    /**
     * 查询用户详情
     * @param userId
     * @return
     */
    @Override
    public Result getUserInfo(Integer userId) {
        User user = userDao.selectByPrimaryKey(userId);
        return new Result().success("item", user);
    }

    /**
     * 修改用户信息
     * @param userUpdateVO
     * @return
     */
    @Override
    public Result updateUserInfo(UserUpdateVO userUpdateVO) {

        User user = new User();
        BeanUtils.copyProperties(userUpdateVO, user);
        int i = userDao.updateByPrimaryKeySelective(user);
        if(i <= 0){
            return Result.error().message("修改用户失败！");
        }
        return Result.ok();
    }

    /**
     * 查询用户列表
     * @return
     */
    @Override
    public Result getUserPageList(Integer pageNum, Integer pageSize, UserQueryVO userQueryVO) {
        // 分页查询用户列表
        PageHelper.startPage(pageNum,pageSize);
        List<UserDTO> users = userDao.getUserPageList(userQueryVO);
        return new Result().pageSuccess(users);
    }
}
