package com.zhu.base.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.entity.CourseTime;
import com.zhu.base.entity.PlanRecord;
import com.zhu.base.service.ScheduleClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/5 15:07
 *     email        1092478224@qq.com
 *     desc         排课管理
 * </pre>
 */
@RestController
@RequestMapping("/schedule-class")
public class ScheduleClassController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleClassController.class);

    @Autowired
    private ScheduleClassService scheduleClassService ;

    /*
     * *****************************排课预设***********************************
     */

    /**
     * 预设时间列表
     *
     * @author zwy
     * @date 2018/12/7 17:39
     */
    @RequestMapping(value = "/pre/list")
    @GET
    public Map queryPresetTimeTable(Integer page,Integer size){
        Map<String,Object> courseTimes = scheduleClassService.findCourseTimes(page,size);
        return ResultUtils.success(courseTimes);
    }

    /**
     * 排课之前的全校课程时间段预设(批量添加)
     *
     * @author zwy
     * @date 2018/12/5 15:23
     */
    @RequestMapping(value = "/pre/course-time")
    @POST
    public Map presetCourseTime(@RequestParam(value = "timeSlot[]") List<CourseTime> timeSlot){
        scheduleClassService.presetCourseTime(timeSlot);
        return ResultUtils.success() ;
    }

    /**
     * 排课预设
     *
     * @author zwy
     * @date 2018/12/7 16:30
     */
    @RequestMapping(value = "/pre/coursetimebyone")
    @POST
    public Map presetCourseTimeByOne(CourseTime courseTime){
        return scheduleClassService.addCourseTime(courseTime);
    }

    /**
     * 删除预设课时
     *
     * @author zwy
     * @date 2019/3/1 14:44
     */
    @RequestMapping("/pre/delete")
    @DELETE
    public Map deletePreCourseTime(Integer id){
        return ResultUtils.success(scheduleClassService.deletePreCourseTime(id));
    }

    /**
     *
     *
     * @author zwy
     * @date 2019/3/1 15:10
     */
    @RequestMapping("/pre/update")
    @POST
    public Map updatePreCourseTime(CourseTime courseTime){
        return scheduleClassService.updateTimeSlot(courseTime);
    }

    /**
     *
     * 查询课时详情
     *
     * @author zwy
     * @date 2019/3/1 15:11
     */
    @RequestMapping("/pre/detail")
    @GET
    public Map detail(Integer id){
        return ResultUtils.success(scheduleClassService.qeuryTimeSlotById(id));
    }

    /*
     *  ----------               排课               -------------------
     */

    /**
     * 排课列表
     *
     * @author zwy
     * @date 2018/12/8 12:39
     */
    @RequestMapping(value = "/plan-record")
    @GET
    public Map toPlanRecordPage(Integer classId){
        List<PlanRecord> planRecords = scheduleClassService.queryAllPlanRecord(classId);
        Map map = new HashMap();
        map.put("list",new PageInfo<>(planRecords));
        map.put("preset",scheduleClassService.queryAllActiveTimes());
        map.put("classroom",scheduleClassService.queryClassRoom());
        return ResultUtils.success(map);
    }

    /**
     * 排课
     *
     * @author zwy
     * @date 2018/12/6 9:16
     */
    @RequestMapping(value = "/schedule-course")
    @POST
    public Map scheduleCourse(PlanRecord planRecord, HttpSession httpSession){
        int ifSucess = scheduleClassService.scheduleCourse(planRecord,getLoginUser(httpSession));
        return ifSucess > 0 ? ResultUtils.success("保存成功！"):ResultUtils.error("该排课时间有冲突") ;
    }

    /**
     * 课表详情查看
     *
     * @author zwy
     * @date 2019/3/1 16:30
     */
    @RequestMapping("/detail")
    @GET
    public Map scheduleCourseDetail(Integer id){
        Map<String,Object> planRecord = scheduleClassService.queryPlanRecordById(id);
        return ResultUtils.success(planRecord) ;
    }

    /**
     * 排课修改
     *
     * @author zwy
     * @date 2019/3/4 13:47
     */
    @RequestMapping("/update")
    @POST
    public Map updateSchedule(PlanRecord planRecord){
        int ifSucess = scheduleClassService.updatePlanRecord(planRecord) ;
        return ifSucess > 0 ? ResultUtils.success("保存成功！"):ResultUtils.error("该排课时间有冲突") ;
    }

    /**
     * 课表删除
     *
     * @author zwy
     * @date 2019/3/5 14:53
     */
    @RequestMapping("/delete")
    @POST
    public Map deleteSchedule(Integer planId){
        return ResultUtils.success(scheduleClassService.deleteSchedule(planId)) ;
    }
}
