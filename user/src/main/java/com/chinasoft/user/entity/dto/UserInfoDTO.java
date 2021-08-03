package com.chinasoft.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: VanceChen
 * @Date: 2021/8/2 11:04
 * @Description: TODO
 **/
@Data
@ApiModel(value = "注册对象", description = "注册对象")
public class UserInfoDTO implements Serializable {

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @ApiModelProperty(value = "性别(F女、M男、N未知)")
    private String gender;

    @ApiModelProperty(value = "状态（VALID/FROZEN/DEL)")
    private String status;

    @ApiModelProperty(value = "最后登录IP")
    private String lastLoginIp;

    @ApiModelProperty(value = "最后登录设备")
    private String lastLoginEqpt;

}
