package com.chinasoft.common.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: VanceChen
 * @Date: 2021/8/2 14:12
 * @Description: TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "异常码")
    private String msg;

    @Override
    public String toString() {
        return "CommonException{" +
                "message=" + this.getMessage() +
                ", code=" + code +
                '}';
    }
}
