package com.chinasoft.user.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: VanceChen
 * @Date: 2021/8/2 11:04
 * @Description: TODO
 **/
@Data
@ApiModel(value = "注册对象", description = "注册对象")
public class RegisterVO implements Serializable {

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "出生日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthday;

    @ApiModelProperty(value = "性别(F女、M男、N未知)")
    private String gender;

}
