package com.chinasoft.user.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: VanceChen
 * @Date: 2021/8/2 11:02
 * @Description: TODO
 **/
@Data
@ApiModel(value="登录对象", description="登录对象")
public class LoginVO implements Serializable {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String pwd;
}
