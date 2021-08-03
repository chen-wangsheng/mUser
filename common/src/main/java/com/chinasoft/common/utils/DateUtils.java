package com.chinasoft.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: VanceChen
 * @Date: 2021/7/29 9:22
 * @Description: 日期工具类
 **/
public class DateUtils {

    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

    /**
     * @throws
     * @Title: formatDateTime
     * @Description: 格式化日期时间(yyyy-MM-dd HH:mm:ss)
     * @param: [dateTime]
     * @return: java.lang.String
     */
    public static String formatDateTime(Date dateTime) {
        return DATE_TIME_FORMAT.format(dateTime);
    }

    /**
     * @throws
     * @Title: formatDate
     * @Description: 格式化日期(yyyy-MM-dd)
     * @param: [date]
     * @return: java.lang.String
     */
    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    /**
     * @throws
     * @Title: formatTime
     * @Description: 格式化时间(HH:mm:ss)
     * @param: [time]
     * @return: java.lang.String
     */
    public static String formatTime(Date time) {
        return TIME_FORMAT.format(time);
    }

    /**
     * @throws
     * @Title: getCurrentTime
     * @Description: 获取当前时间(HH:mm:ss)
     * @param: []
     * @return: java.lang.String
     */
    public static String getCurrentTime() {
        return TIME_FORMAT.format(System.currentTimeMillis());
    }

    /**
     * @throws
     * @Title: getTodayDate
     * @Description: 获取今天日期(yyyy-MM-dd)
     * @param: []
     * @return: java.lang.String
     */
    public static String getTodayDate() {
        return DATE_FORMAT.format(System.currentTimeMillis());
    }

    /**
     * @throws
     * @Title: getYesterdayDate
     * @Description: 获取昨天日期(yyyy-MM-dd)
     * @param: []
     * @return: java.lang.String
     */
    public static String getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        Date date = calendar.getTime();

        return DATE_FORMAT.format(date);
    }

    /**
     * @throws
     * @Title: getYesterdayDate
     * @Description: 获取下一天日期(yyyy-MM-dd)
     * @param: []
     * @return: java.lang.String
     */
    public static String getNextDate(Date date, int nextDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date == null ? new Date() : date);
        calendar.add(Calendar.DAY_OF_YEAR, nextDays);

        Date nextDate = calendar.getTime();

        return DATE_FORMAT.format(nextDate);
    }

    /**
     * @throws
     * @Title: before
     * @Description: 判断一个时间是否在另一个时间之前
     * @param: [dateTime1, dateTime2]
     * @return: boolean
     */
    public static boolean before(String dateTime1, String dateTime2) {
        try {
            Date dt1 = DATE_TIME_FORMAT.parse(dateTime1);
            Date dt2 = DATE_TIME_FORMAT.parse(dateTime2);

            if (dt1.before(dt2)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @throws
     * @Title: after
     * @Description: 判断一个时间是否在另一个时间之后
     * @param: [dateTime1, dateTime2]
     * @return: boolean
     */
    public static boolean after(String dateTime1, String dateTime2) {
        try {
            Date dt1 = DATE_TIME_FORMAT.parse(dateTime1);
            Date dt2 = DATE_TIME_FORMAT.parse(dateTime2);

            if (dt1.after(dt2)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @throws
     * @Title: parseDateTime
     * @Description: 解析日期时间字符串
     * @param: [dateTime]
     * @return: java.util.Date
     */
    public static Date parseDateTime(String dateTime) {
        try {
            return DATE_TIME_FORMAT.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @throws
     * @Title: parseDate
     * @Description: 解析日期时间字符串
     * @param: [dateTime]
     * @return: java.util.Date
     */
    public static Date parseDate(String dateTime) {
        try {
            return DATE_FORMAT.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
