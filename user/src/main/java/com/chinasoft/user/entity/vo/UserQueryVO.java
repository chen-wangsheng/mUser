package com.chinasoft.user.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: VanceChen
 * @Date: 2021/7/30 14:21
 * @Description: TODO
 **/
@Data
@ApiModel(value="userQueryVO", description="查询user对象")
public class UserQueryVO implements Serializable {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别(F女、M男、N未知)")
    private String gender;

    @ApiModelProperty(value = "登录时间")
    private String begin;

    @ApiModelProperty(value = "退出时间")
    private String end;

}
