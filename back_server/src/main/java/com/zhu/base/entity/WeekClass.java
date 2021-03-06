package com.zhu.base.entity;


import java.util.HashMap;
import java.util.Map;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/10 15:50
 *     email        1092478224@qq.com
 *     desc         课程表组合
 * </pre>
 */
public class WeekClass {

    private Map<String ,Object> getMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("classes",null);
        map.put("planId",null);
        return map ;
    }

    //教室
    private String classroom ;

    //时间段
    private String timeSlot ;

    //班级名称 + 授课老师名称
    private Map<String,Object> monday = getMap();

    private Map<String,Object> tuesday = getMap();

    private Map<String,Object> wednesday = getMap();

    private Map<String,Object> thursday = getMap();

    private Map<String,Object> friday = getMap();

    private Map<String,Object> saturday = getMap();

    private Map<String,Object> sunday = getMap();

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Map<String, Object> getMonday() {
        return monday;
    }

    public void setMonday(Map<String, Object> monday) {
        this.monday = monday;
    }

    public Map<String, Object> getTuesday() {
        return tuesday;
    }

    public void setTuesday(Map<String, Object> tuesday) {
        this.tuesday = tuesday;
    }

    public Map<String, Object> getWednesday() {
        return wednesday;
    }

    public void setWednesday(Map<String, Object> wednesday) {
        this.wednesday = wednesday;
    }

    public Map<String, Object> getThursday() {
        return thursday;
    }

    public void setThursday(Map<String, Object> thursday) {
        this.thursday = thursday;
    }

    public Map<String, Object> getFriday() {
        return friday;
    }

    public void setFriday(Map<String, Object> friday) {
        this.friday = friday;
    }

    public Map<String, Object> getSaturday() {
        return saturday;
    }

    public void setSaturday(Map<String, Object> saturday) {
        this.saturday = saturday;
    }

    public Map<String, Object> getSunday() {
        return sunday;
    }

    public void setSunday(Map<String, Object> sunday) {
        this.sunday = sunday;
    }
}
