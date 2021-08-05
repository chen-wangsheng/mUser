package com.chinasoft.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: VanceChen
 * @Date: 2021/7/30 11:38
 * @Description: TODO
 **/
@Data
@ApiModel(value="EduVideo对象", description="课程视频")
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 4738405251460060638L;

    @Id
    private Integer id;

    @ApiModelProperty(value = "手机号")
    private String mobile;

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
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "性别(F女、M男、N未知)")
    private String gender;

    @ApiModelProperty(value = "创建人")
    private Integer createBy;

    @ApiModelProperty(value = "状态（VALID/FROZEN/DEL)")
    private String status;

}
