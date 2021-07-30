package com.chinasoft.user.dao;

import com.chinasoft.user.base.BaseDao;
import com.chinasoft.user.entity.dto.UserDTO;
import com.chinasoft.user.entity.vo.UserQueryVO;
import com.chinasoft.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: VanceChen
 * @Date: 2021/7/29 18:12
 * @Description: TODO
 **/
@Repository
public interface UserDao extends BaseDao<User> {

    /**
     * 查询用户列表
     * @param userQueryVO
     * @return
     */
    List<UserDTO> getUserPageList(UserQueryVO userQueryVO);

}