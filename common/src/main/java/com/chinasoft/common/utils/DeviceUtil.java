package com.chinasoft.common.utils;

import org.springframework.mobile.device.Device;

/**
 * @Author: VanceChen
 * @Date: 2021/8/3 14:00
 * @Description: TODO
 **/
public class DeviceUtil {
    public static String getdevice(Device device){
        if (device.isMobile()) {
            System.out.println("========请求来源设备是手机！========");
            return "手机";
        } else if (device.isTablet()) {
            System.out.println("========请求来源设备是平板！========");
            return "平板";
        } else if(device.isNormal()){
            System.out.println("========请求来源设备是PC！========");
            return "PC";
        }else {
            System.out.println("========请求来源设备是其它！========");
            return "其他";
        }
    }
}
