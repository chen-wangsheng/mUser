package com.chinasoft.user.service.impl;

import com.chinasoft.user.exception.CommonException;
import com.chinasoft.common.jwt.JwtUtils;
import com.chinasoft.common.md5.MD5Utils;
import com.chinasoft.common.utils.DateUtils;
import com.chinasoft.user.utils.RedisUtils;
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
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 删除用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public Result delUserInfo(Integer userId) {
        int i = userDao.deleteByPrimaryKey(userId);
        if (i <= 0) {
            return Result.error().message("删除用户失败！");
        }
        return Result.ok();
    }

    /**
     * 查询用户详情
     *
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
     *
     * @param userUpdateVO
     * @return
     */
    @Override
    public Result updateUserInfo(UserUpdateVO userUpdateVO) {

        User user = new User();
        BeanUtils.copyProperties(userUpdateVO, user);
        Date date = DateUtils.parseDate(userUpdateVO.getBirthday());
        user.setBirthday(date);
        user.setUpdateTime(new Date());
        int i = userDao.updateByPrimaryKeySelective(user);
        if (i <= 0) {
            return Result.error().message("修改用户失败！");
        }
        return Result.ok();
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    @Override
    public Result getUserPageList(Integer pageNum, Integer pageSize, UserQueryVO userQueryVO) {
        // 分页查询用户列表
        PageHelper.startPage(pageNum, pageSize);
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
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        //校验参数
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new CommonException(20001, "用户名或密码为空");
        }
        //获取会员
        User user = new User();
        user.setMobile(username);
        User selectOne = userDao.selectOne(user);
        if (selectOne == null) {
            throw new CommonException(20001, "用户不存在");
        }
        //校验密码
        if (!MD5Utils.getSaltverifyMD5(password, selectOne.getPwd())) {
            String key = "user-login-time:" + username;
            if(!redisUtils.hasKey(key)){
                redisUtils.setEx("user-login-time:" + username, "0", 1, TimeUnit.DAYS);
            }
            throw new CommonException(20001, "密码错误");
        }
        String token = JwtUtils.getJwtToken(username, password);
        return token;
    }

    /**
     * 用户注册
     *
     * @param registerVO
     */
    @Override
    @Transactional
    public void regist(RegisterVO registerVO) {
        //校验参数
        if (StringUtils.isEmpty(registerVO.getMobile()) ||
                StringUtils.isEmpty(registerVO.getPwd())) {
            throw new CommonException(20001, "error");
        }
        //获取会员
        User user = new User();
        user.setMobile(registerVO.getMobile());
        User selectOne = userDao.selectOne(user);
        if (selectOne != null) {
            throw new CommonException(20001, "手机号已被注册！");
        }
        BeanUtils.copyProperties(registerVO, user);
        if (StringUtils.isNotEmpty(registerVO.getBirthday())) {
            user.setBirthday(DateUtils.parseDate(registerVO.getBirthday()));
        }
        user.setPwd(MD5Utils.getSaltMD5(registerVO.getPwd()));
        user.setStatus("VALID");
        user.setLastFailedTimes(0);
        user.setCreateBy(-1);
        user.setCreateTime(new Date());
        int insert = userDao.insert(user);
        if (insert <= 0) {
            throw new CommonException(20001, "注册失败！");
        }
    }

    /**
     * 修改设备信息
     *
     * @param mobile          手机号
     * @param loginDevice     登录设备
     * @param ipAddr          ip
     * @param lastFailedTimes 累计失败次数
     */
    @Override
    @Transactional
    public void updateDeviceInfo(String mobile, String loginDevice, String ipAddr, Date date, int lastFailedTimes) {
        User user = new User();
        user.setLastLoginEqpt(loginDevice);
        user.setLastLoginIp(ipAddr);
        user.setLastFailedTimes(lastFailedTimes);
        user.setLastLoginTime(date);
        user.setUpdateTime(new Date());
        user.setUpdateBy(-1);
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("mobile", mobile);
        userDao.updateByExampleSelective(user, example);
    }

    @Override
    public void updateById(Integer userId) {
        User user = new User();
        user.setLastLoginTime(new Date());
        userDao.updateByPrimaryKeySelective(user);
    }

    /**
     * 查询用户错误次数
     *
     * @param mobile
     * @return
     */
    @Override
    public Long getFailedTime(String mobile) {
        String key = "user-login-time:" + mobile;
        if (redisUtils.hasKey(key)) {
            return redisUtils.incrBy("user-login-time:" + mobile, 1L);
        }
        User user = new User();
        user.setMobile(mobile);
        User selectOne = userDao.selectOne(user);
        if (selectOne == null) {
            throw new RuntimeException("用户不存在");
        }
        long count = Long.valueOf(selectOne.getLastFailedTimes());
        redisUtils.setEx(key, "1", 1, TimeUnit.DAYS);
        return count;
    }

    /**
     * 失败5次锁定账号
     * @param mobile
     */
    @Override
    public void updateStatus(String mobile, Integer times) {
        User user = new User();
        user.setLastFailedTimes(times);
        user.setStatus("DEL");
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("mobile", mobile);
        userDao.updateByExampleSelective(user, example);
    }
}
