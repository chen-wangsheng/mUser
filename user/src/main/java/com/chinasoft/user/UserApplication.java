package com.chinasoft.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: VanceChen
 * @Date: 2021/7/29 18:02
 * @Description: 用户模块启动类
 **/
@SpringBootApplication
@MapperScan("com.chinasoft.user.dao")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
