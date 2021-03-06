package com.zhu.base.common;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author pc
 */
public class TimeUtil {

    /**
     * 获取现在时间
     *
     * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentTime);
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(currentTime);
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        return formatter.format(currentTime);
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String dateToStrLong(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(dateDate);
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     */
    public static String dateToStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(dateDate);
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 得到现在时间
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 提取一个月中的最后一天
     */
    public static Date getLastDate(long day) {
        Date date = new Date();
        long date3Hm = date.getTime() - 3600000 * 34 * day;
        return new Date(date3Hm);
    }

    /**
     * 得到现在时间
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        return formatter.format(currentTime);
    }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     */
    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        return formatter.format(currentTime);
    }

    /**
     * 日期比较（形如 2018-11-29 的日期）
     *
     * @author zwy
     * @date 2018/11/29 16:23
     */
    public static boolean compareDate(String date1,String date2) {
        LocalDate localDate1 = LocalDate.parse(date1);
        LocalDate localDate2 = LocalDate.parse(date2);
        return localDate1.compareTo(localDate2) > 0  ;
    }

    /**
     * 在当前日期的基础上添加年份
     *
     * @author zwy
     * @date 2018/11/30 18:33
     */
    public static String plusYear(String date,int year){
        LocalDate localDate = LocalDate.parse(date);
        date = localDate.plusYears(year).toString();
        return date ;
    }

    /**
     * 时间比较（形如 09:00 的时间）
     *
     * @author zwy
     * @date 2018/12/6 14:30
     */
    public static boolean compareTime(String time1,String time2){
        LocalTime localTime1 = LocalTime.parse(time1);
        LocalTime localTime2 = LocalTime.parse(time2);
        return localTime1.compareTo(localTime2)>0;
    }

    /**
     * 计算年龄
     *
     * @author zwy
     * @date 2018/12/24 11:04
     */
    public static Integer calculateAge(String birthDate){
        LocalDate age = LocalDate.now();
        String year = birthDate.substring(0,4) ;
        String month = birthDate.substring(4,6) ;
        String day = birthDate.substring(6);
        LocalDate birth = LocalDate.parse(year+"-"+month+"-"+day);
        return  age.compareTo(birth);
    }

    public static Integer calAge(String birthDate){
        LocalDate age = LocalDate.now();
        LocalDate birth = LocalDate.parse(birthDate.substring(0,10));
        return  age.compareTo(birth);
    }
}