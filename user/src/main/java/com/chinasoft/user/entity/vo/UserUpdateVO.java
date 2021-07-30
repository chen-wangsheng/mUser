package com.chinasoft.user.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: VanceChen
 * @Date: 2021/7/30 11:30
 * @Description: TODO
 **/
@Data
@ApiModel(value="EduVideo对象", description="课程视频")
public class UserUpdateVO implements Serializable {

    private static final long serialVersionUID = 3108507741216646272L;

    @Id
    private Integer id;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "性别(F女、M男、N未知)")
    private String gender;


}
