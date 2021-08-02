package com.chinasoft.common.utils;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2021/7/30 11:21
 * @Version 10.21
 * @Author vanceChen
 */
@Data
@ApiModel(value = "Result", description = "返回说明")
public class Result {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Object data;

    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }


    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("失败");
        return result;
    }


    /**
     * @param success
     * @return 使用this从而达到连式编程
     */
    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }


    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public Result success(String message, Object data) {
        this.code = ResultCode.SUCCESS;
        this.message = message;
        this.data = data;
        this.success = true;
        return this;
    }

    /**
     * 返回分页成功数据
     *
     * @param data
     * @return
     */
    public Result pageSuccess(List data) {
        PageInfo pageInfo = new PageInfo(data);
        Map<String, Object> result = new HashMap<>();
        result.put("pageSize", pageInfo.getPageSize());
        result.put("totalPage", pageInfo.getPages());
        result.put("total", pageInfo.getTotal());
        result.put("pageNum", pageInfo.getPageNum());
        result.put("list", pageInfo.getList());
        this.code = ResultCode.SUCCESS;
        this.message = "操作成功";
        this.data = result;
        this.success = true;
        return this;
    }

    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
