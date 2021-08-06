package com.chinasoft.user.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.chinasoft.common.md5.MD5Utils;
import com.chinasoft.user.entity.User;
import com.chinasoft.user.entity.dto.UserData;
import com.chinasoft.user.exception.CommonException;
import com.chinasoft.user.service.UserService;

import java.util.Date;
import java.util.Map;

/**
 * @Author: VanceChen
 * @Date: 2021/8/5 15:41
 * @Description: TODO
 **/
public class UserExcelListener extends AnalysisEventListener<UserData> {

    public UserService userService;

    public UserExcelListener(){}

    // 有参构造,用于操作数据库
    public UserExcelListener(UserService userService){
        this.userService = userService;
    }
    //一行一行去读取excle内容
    @Override
    public void invoke(UserData user, AnalysisContext analysisContext) {
        if(user == null) {
            throw new CommonException(20001,"添加失败");
        }
        if(!exitUser(user.getMobile())){
            User addUser = new User();
            addUser.setMobile(user.getMobile());
            addUser.setPwd(MD5Utils.getSaltMD5("12345678"));
            addUser.setLastFailedTimes(0);
            addUser.setNickname(user.getNickname());
            addUser.setBirthday(user.getBirthday());
            addUser.setHeadImg("https://cos-1258886224.cos.ap-guangzhou.myqcloud.com/avatar/83a1303c-4.jpeg");
            addUser.setGender(user.getGender());
            addUser.setStatus(user.getStatus());
            addUser.setCreateTime(new Date());
            addUser.setCreateBy(0);

            userService.save(addUser);
        }
    }
    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }
    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    /**
     * 查询是否存在手机号
     */
    public boolean exitUser(String mobile){
        return userService.exitUser(mobile);
    }
}
