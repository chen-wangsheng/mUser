package com.chinasoft.common.utils;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Date 2021/7/30 11:21
 * @Version 10.21
 * @Author vanceChen
 * @Description: 状态码
 */
@ApiModel(value="code",description="状态码")
public interface ResultCode {

    // 自定义成功的响应码
    @ApiModelProperty(value="操作成功",name="状态码:200")
    public static Integer SUCCESS = 20000;

    // 自定义失败的响应码
    @ApiModelProperty(value="操作失败",name="状态码:20001")
    public static Integer ERROR = 20001;

    @ApiModelProperty(value="认证失败",name="状态码:401")
    public static final Integer UNAUTHORIZED = 401;

    @ApiModelProperty(value="拒绝访问",name="状态码:403")
    public static final Integer NOT_ACCEPTABLE =403;

}
