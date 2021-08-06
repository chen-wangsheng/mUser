package com.chinasoft.user.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: VanceChen
 * @Date: 2021/8/5 15:39
 * @Description: TODO
 **/
@Data
@ApiModel(value="test对象", description="excel")
public class UserData {

    @ExcelProperty(index = 0)
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ExcelProperty(index = 1)
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ExcelProperty(index = 2)
    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @ExcelProperty(index = 3)
    @ApiModelProperty(value = "性别(F女、M男、N未知)")
    private String gender;

    @ExcelProperty(index = 4)
    @ApiModelProperty(value = "状态（VALID/FROZEN/DEL)")
    private String status;

}
