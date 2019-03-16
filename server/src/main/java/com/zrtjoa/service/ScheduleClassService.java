package com.zrtjoa.service;

import com.zrtjoa.entity.Classroom;
import com.zrtjoa.entity.CourseTime;
import com.zrtjoa.entity.PlanRecord;
import com.zrtjoa.entity.Teacher;

import java.util.List;
import java.util.Map;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/5 15:12
 *     email        1092478224@qq.com
 *     desc
 * </pre>
 */
public interface ScheduleClassService {

    /**
     * 排课之前的时间预设
     *
     * @author zwy
     * @date 2018/12/5 15:47
     * @param timeSlot 时间段
     * @return int
     */
    Integer presetCourseTime(List<CourseTime> timeSlot) ;

    /**
     * 排课记录及生成课程
     *
     * @author zwy
     * @date 2018/12/6 9:16
     * @param planRecord 排课记录
     * @param teacher 当前
     * @return int
     */
    Integer scheduleCourse(PlanRecord planRecord, Teacher teacher);

    /**
     * 时间段预设（单条）
     *
     * @author zwy
     * @date 2018/12/7 16:45
     * @param courseTime 时间段实体
     * @return int
     */
    Map addCourseTime(CourseTime courseTime);

    /**
     * 查询课程时间预设表
     *
     * @author zwy
     * @date 2018/12/7 17:41
     * @return list
     */
    List<CourseTime> findCourseTimes();

    /**
     * 查询排课记录
     *
     * @author zwy
     * @date 2018/12/8 12:44
     * @return list
     */
    List<PlanRecord> queryAllPlanRecord(Integer classId);

    /**
     * 查询全部可用时间段
     *
     * @author zwy
     * @date 2018/12/8 12:58
     * @return list
     */
    List<CourseTime> queryAllActiveTimes();

    /**
     * 获取教室
     *
     * @author zwy
     * @date 2018/12/8 13:58
     * @return list
     */
    List<Classroom> queryClassRoom();

    /**
     * 删除预设课时
     *
     * @author zwy
     * @date 2019/3/1 14:45
     * @param
     */
    Integer deletePreCourseTime(Integer id);

    /**
     * 根据id查询详情
     *
     * @author zwy
     * @date 2019/3/1 15:14
     * @param id 课时id
     * @return courseT
     */
    CourseTime qeuryTimeSlotById(Integer id);

    /**
     * 更新课时时间段
     *
     * @author zwy
     * @date 2019/3/1 15:34
     * @param courseTime 课程时间段
     * @return map
     */
    Map updateTimeSlot(CourseTime courseTime);

    /**
     * 查询具体课程
     *
     * @author zwy
     * @date 2019/3/1 16:41
     */
    Map<String,Object> queryPlanRecordById(Integer id);

    /**
     * 修改排课计划
     *
     * @author zwy
     * @date 2019/3/4 13:57
     */
    Integer updatePlanRecord(PlanRecord planRecord);

    /**
     * 根据id删除课程
     *
     * @author zwy
     * @date 2019/3/5 14:30
     */
    Integer deleteSchedule(Integer planId);
}
