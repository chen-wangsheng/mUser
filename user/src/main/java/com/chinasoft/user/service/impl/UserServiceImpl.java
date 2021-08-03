package com.chinasoft.user.service.impl;

import com.chinasoft.common.exception.CommonException;
import com.chinasoft.common.jwt.JwtUtils;
import com.chinasoft.common.utils.DateUtils;
import com.chinasoft.common.utils.Result;
import com.chinasoft.common.utils.StringUtils;
import com.chinasoft.user.dao.UserDao;
import com.chinasoft.user.entity.User;
import com.chinasoft.user.entity.dto.UserDTO;
import com.chinasoft.user.entity.dto.UserInfoDTO;
import com.chinasoft.user.entity.vo.RegisterVO;
import com.chinasoft.user.entity.vo.UserQueryVO;
import com.chinasoft.user.entity.vo.UserUpdateVO;
import com.chinasoft.user.service.UserService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        Date date = DateUtils.parseDate(userUpdateVO.getBirthday());
        user.setBirthday(date);
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

    @Override
    public UserInfoDTO getUserLoginInfo(String mobile) {
        UserInfoDTO user = userDao.getUserLoginInfo(mobile);
        return user;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        String token = JwtUtils.getJwtToken(username, password);
        return token;
    }

    /**
     * 用户注册
     * @param registerVO
     */
    @Override
    public void regist(RegisterVO registerVO) {
        User user = new User();
        BeanUtils.copyProperties(registerVO, user);
        if(StringUtils.isNotEmpty(registerVO.getBirthday())){
            user.setBirthday(DateUtils.parseDate(registerVO.getBirthday()));
        }
        user.setStatus("VALID");
        user.setLastFailedTimes(0);
        user.setCreateBy(-1);
        user.setCreateTime(new Date());
        int insert = userDao.insert(user);
        if(insert <= 0){
            throw new CommonException(20001, "注册失败！");
        }
    }
}
