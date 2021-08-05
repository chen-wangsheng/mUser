package com.chinasoft.common.utils;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author: VanceChen
 * @Date: 2021/8/5 10:49
 * @Description: TODO
 **/
public class RandomNumberUtils {

    public static String getRandomString(){
        StringBuilder sb = new StringBuilder();
        sb.append(LocalDateTime.now().getYear());
        int month = LocalDateTime.now().getMonth().getValue();
        sb.append(month>=10?month:'0'+month);
        sb.append(LocalDateTime.now().getDayOfMonth());
        sb.append(Math.abs(UUID.randomUUID().getLeastSignificantBits()%1_00_00_000_000L));
        return sb.toString();
    }
}
