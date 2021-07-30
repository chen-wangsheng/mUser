package com.chinasoft.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_user")
@ApiModel(value="EduVideo对象", description="课程视频")
public class User implements Serializable {

    private static final long serialVersionUID = -4428320010893838111L;

    @Id
    private Integer id;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码（密文）")
    private String pwd;

    @ApiModelProperty(value = "密码盐")
    private Integer pwdSalt;

    @ApiModelProperty(value = "最后登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "最后登录IP")
    private String lastLoginIp;

    @ApiModelProperty(value = "最后登录设备")
    private String lastLoginEqpt;

    @ApiModelProperty(value = "最后累计登录失败次数")
    private Integer lastFailedTimes;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "性别(F女、M男、N未知)")
    private String gender;

    @ApiModelProperty(value = "状态（VALID/FROZEN/DEL)")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private Integer createBy;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新人")
    private Integer updateBy;
}