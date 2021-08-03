package com.chinasoft.user.exception;

import com.chinasoft.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: vanceChen
 * @create: 2021/7/1
 * @Describe: 统一异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.error().message("方法执行ArithmeticException异常！");
    }

    /**
     * 自定义的异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public Result error(CommonException e){
//        log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return Result.error().message(e.getMsg()).code(e.getCode());
    }
}

