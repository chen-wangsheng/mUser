package com.chinasoft.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author: VanceChen
 * @Date: 2021/8/2 11:04
 * @Description: TODO
 **/
@Data
@ApiModel(value = "注册对象", description = "注册对象")
public class UserInfoDTO implements Serializable {

    @ApiModelProperty(value = "用户id")
    private Integer memberId;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "头像")
    private String headImg;

}
