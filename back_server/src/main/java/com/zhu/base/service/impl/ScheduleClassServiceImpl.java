package com.zhu.base.service.impl;

import com.zhu.base.annotation.PagingQuery;
import com.zhu.base.constant.SysConstant;
import com.zhu.base.entity.*;
import com.zhu.base.service.ClassRoomService;
import com.zhu.base.service.ScheduleClassService;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.dao.ClassesDao;
import com.zhu.base.dao.CoursesDao;
import com.zhu.base.dao.PlanRecordDao;
import com.zhu.base.dao.initializingDao.CourseTimeInitializingDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhu.base.common.TimeUtil.compareTime;
import static com.zhu.base.dao.initializingDao.CourseTimeInitializingDao.*;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/5 15:15
 *     email        1092478224@qq.com
 *     desc
 * </pre>
 */
@Service
public class ScheduleClassServiceImpl implements ScheduleClassService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleClassServiceImpl.class);

    @Autowired
    private CourseTimeInitializingDao courseTimeInitializingDao ;

    @Autowired
    private PlanRecordDao planRecordDao ;

    @Autowired
    private CoursesDao coursesDao ;

    @Autowired
    private TransactionTemplate transactionTemplate ;

    @Autowired
    private ClassRoomService classRoomService ;

    @Autowired
    private ClassesDao classesDao ;

    /**
     * 课时预设
     *
     * @author zwy
     * @date 2018/12/6 15:15
     */
    @Override
    public Integer presetCourseTime(List<CourseTime> timeSlot){

        logger.info("--||--课时预设-------------------||-----");

        Integer n = 1 ;
        //时间对比
        for(int i=0;i<timeSlot.size()-1;i++) {
            String[] strings = timeSlot.get(i).getTime().split(SysConstant.Punctuation.SHORT_LINE);
            if(compareTime(strings[0],strings[1])){
                logger.error("-------||-----结束时间小于开始时间-----||---");
                throw new IllegalArgumentException("结束时间小于开始时间");
            }
            for (int j=i+1;j<timeSlot.size();j++){
                String[] strings1 = timeSlot.get(j).getTime().split(SysConstant.Punctuation.SHORT_LINE);
                if(compareTime(strings1[0],strings1[1])){
                    logger.error("-------||-----结束时间小于开始时间-----||---");
                    throw new IllegalArgumentException("结束时间小于开始时间");
                }
                if(compareTime(strings[1],strings1[0])){
                    logger.error("-------||-----开始时间小于上段结束时间-----||---");
                    throw new IllegalArgumentException("开始时间小于上段结束时间");
                }
            }
            //设置时间段代表
            timeSlot.get(i).setType(n.toString());
            n++;
        }
        timeSlot.get(timeSlot.size()-1).setType(n.toString());
        return courseTimeInitializingDao.savePresetCourseTime(timeSlot);
    }

    /**
     * 排课
     *
     * @author zwy
     * @date 2018/12/7 15:40
     */
    @Override
    public Integer scheduleCourse(PlanRecord planRecord, Teacher teacher) {

        if(checkCanBeUpdate(planRecord)<0){
            return checkCanBeUpdate(planRecord);
        }

        Classes classes = classesDao.selectByPrimaryKey(planRecord.getClassid());

        CourseTime newCourseTime = queryTimeSlotBySlotId(planRecord.getCourseid());

        //排课人
        planRecord.setTname(teacher.getTname());
        planRecord.setTid(teacher.getId());
        planRecord.setTeacher(classes.getTname());

        //课程表实体
        Courses courses = new Courses();
        //教室名称，视情况决定后续是否添加教室id
        courses.setClassroom(planRecord.getClassroom());
        courses.setRoomid(planRecord.getRoomId());
        courses.setWeek(planRecord.getWeek());
        courses.setClasses(planRecord.getClassname());
        courses.setClassesid(planRecord.getClassid());
        //授课老师名称，授课老师id后期视情况决定是否添加
        courses.setTeacher(classes.getTname());
        courses.setTid(teacher.getId());
        courses.setTname(teacher.getTname());
        courses.setDate(newCourseTime.getTime());
        courses.setRoomid(planRecord.getRoomId());

        return  transactionTemplate.execute(insert-> {
            int isSuccess = 0;
            logger.info("---||---保存排课记录--------------");
            //保存排课记录
            isSuccess += planRecordDao.insertSelective(planRecord);
            //保存课程，形成课表
            logger.info("---||---保存课程表---------");
            courses.setPlanId(planRecord.getId());
            isSuccess += coursesDao.insertSelective(courses);
            return isSuccess;
        });
    }

    /**
     * 排课前时间段预设（单条）
     *
     * @author zwy
     * @date 2018/12/7 16:46
     */
    @Override
    public Map addCourseTime(CourseTime courseTime) {
        List<String> courseTimes = getActiveTime();
        String startTime = courseTime.getTime().split(SysConstant.Punctuation.SHORT_LINE)[0];
        String endTime = courseTime.getTime().split(SysConstant.Punctuation.SHORT_LINE)[1];
        if(compareTime(startTime,endTime)){
            return ResultUtils.error("时间冲突");
        }
        StringBuilder code = new StringBuilder("0");
        if(courseTimes.size()!=0){
            courseTimes.forEach(ct->{
                String savedStartTime = ct.split(SysConstant.Punctuation.SHORT_LINE)[0];
                String savedEndTime = ct.split(SysConstant.Punctuation.SHORT_LINE)[1];
                if(compareTime(savedEndTime,startTime)&&compareTime(endTime,savedStartTime)
                ) {
                    code.deleteCharAt(0);
                    code.append("1");
                }
            });
        }
        if("1".equals(code.toString())){
            return ResultUtils.error("时间冲突");
        }
        courseTimeInitializingDao.addCourseTime(courseTime);
        return ResultUtils.success();
    }

    @Override
    public Map<String,Object> findCourseTimes(Integer page,Integer size) {
        List<CourseTime> timeSlot = queryAllCourseTime();
        Integer total = timeSlot.size();
        if(page!=null&size!=null){
            int pageSize = page*size>total?total:page*size ;
            timeSlot = timeSlot.subList((page-1)*size,pageSize);
        }
        Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
        map.put("total",total);
        map.put("list",timeSlot);
        map.put("pageNum",page);
        return map;
    }

    @Override
    @PagingQuery
    public List<PlanRecord> queryAllPlanRecord(Integer classId) {
        //根据时间段id查询时间段
        List<PlanRecord> planRecords = planRecordDao.queryAllPlanRecord(classId);
        planRecords.forEach(pr->{
            CourseTime courseTime = queryTimeSlotBySlotId(pr.getCourseid());
            pr.setTimeSlot(courseTime.getTime());
        });
        return planRecords ;
    }

    @Override
    public List<CourseTime> queryAllActiveTimes() {
        return queryActiveCourseTime();
    }

    @Override
    public List<Classroom> queryClassRoom() {
        return classRoomService.queryClassroomList(null);
    }

    @Override
    public Integer deletePreCourseTime(Integer id) {
        return courseTimeInitializingDao.delete(id);
    }

    @Override
    public CourseTime qeuryTimeSlotById(Integer id) {
        return courseTimeInitializingDao.qeuryTimeSlotById(id);
    }

    @Override
    public Map updateTimeSlot(CourseTime courseTime) {
        List<CourseTime> courseTimeList = courseTimeInitializingDao.qeuryAllTimeSlot();
        String startTime = courseTime.getTime().split(SysConstant.Punctuation.SHORT_LINE)[0];
        String endTime = courseTime.getTime().split(SysConstant.Punctuation.SHORT_LINE)[1];
        if(compareTime(startTime,endTime)){
            return ResultUtils.error("时间冲突");
        }
        StringBuilder code = new StringBuilder("0");
        courseTimeList.forEach(ctt->{
            if(!ctt.getId().equals(courseTime.getId())){
                String savedStartTime = ctt.getTime().split(SysConstant.Punctuation.SHORT_LINE)[0];
                String savedEndTime = ctt.getTime().split(SysConstant.Punctuation.SHORT_LINE)[1];
                if(compareTime(savedEndTime,startTime)&&compareTime(endTime,savedStartTime)
                ) {
                    code.deleteCharAt(0);
                    code.append("1");
                }
            }
        });
        if("1".equals(code.toString())){
            return ResultUtils.error("时间冲突");
        }
        courseTimeInitializingDao.updateTimeSlote(courseTime);
        return ResultUtils.success(null,"修改成功");
    }

    @Override
    public Map<String,Object> queryPlanRecordById(Integer id) {
        Map<String,Object> map = new HashMap<>();
        PlanRecord planRecord = planRecordDao.selectByPrimaryKey(id);
        Courses courses = coursesDao.queryCourseByPlanId(id);
        Classroom classRoom = classRoomService.selectByPrimaryKey(courses.getRoomid());
        planRecord.setClassCateName(classRoom==null?null:classRoom.getCatename());
        planRecord.setClassCateId(classRoom==null?null:classRoom.getCateid());
        planRecord.setClassRoomId(classRoom==null?null:classRoom.getId());
        planRecord.setTimeSlot(courses.getDate());
        map.put("planRecord",planRecord);
        map.put("timeSlot",queryAllCourseTime());
        map.put("classCate",classRoomService.queryClassTypeList());
        return map;
    }

    @Override
    public Integer updatePlanRecord(PlanRecord planRecord) {
        PlanRecord existPlan = planRecordDao.queryRecordById(planRecord.getId());
        planRecord.setClassid(existPlan.getClassid());
        if(checkCanBeUpdate(planRecord)<0) {
            return -1;
        }
        CourseTime newCourseTime = queryTimeSlotBySlotId(planRecord.getCourseid());
        //课程表实体
        Courses courses = new Courses();
        //教室名称，视情况决定后续是否添加教室id
        courses.setClassroom(planRecord.getClassroom());
        courses.setWeek(planRecord.getWeek());
        courses.setDate(newCourseTime.getTime());
        courses.setRoomid(planRecord.getRoomId());
        courses.setPlanId(planRecord.getId());

        return  transactionTemplate.execute(insert-> {
            int isSuccess = 0;
            logger.info("---||---更新排课记录--------------");
            //更新排课记录
            isSuccess += planRecordDao.updateByPrimaryKeySelective(planRecord);
            //更新课表
            logger.info("---||---根据排课id更新课程表---------");
            isSuccess += coursesDao.updateByPlanIdSelective(courses);
            return isSuccess;
        });
    }

    @Override
    public Integer deleteSchedule(Integer planId) {
        //后续肯定是课表学期内不可改的，现暂缓
        return  transactionTemplate.execute(delete-> {
            int isSuccess = 0;
            logger.info("---||---更新排课记录--------------");
            //更新排课记录
            isSuccess += planRecordDao.deleteByPrimaryKey(planId);
            //更新课表
            logger.info("---||---根据排课id更新课程表---------");
            isSuccess += coursesDao.deleteByPlanIdSelective(planId);
            return isSuccess;
        });
    }

    private Integer checkCanBeUpdate(PlanRecord planRecord){
        //查询排课表中是否有时间冲突
        //全局缓存有效时间id list
        List<CourseTime> courseTimeList = queryActiveCourseTime();
        //是否选择全天
        if(StringUtils.isBlank(planRecord.getCourseid())){
            logger.info("........选择全天........");
            //选择全天
            List<String> planRecordList = planRecordDao.queryPlanRecordByRecord(planRecord);
            StringBuilder wholeDayTimeSlots = new StringBuilder();
            courseTimeList.forEach(ct->wholeDayTimeSlots.append(SysConstant.Punctuation.COMMA).append(ct.getTime()));

            planRecordList.forEach(pr->{
                //如果全天时间段包含已被安排过的时间段，则删除该时间段
                if (wholeDayTimeSlots.toString().contains(pr)){
                    wholeDayTimeSlots.delete(wholeDayTimeSlots.indexOf(pr),wholeDayTimeSlots.indexOf(pr)+2);
                }
            });

            logger.info(".......可用时间段,[{}]...........",wholeDayTimeSlots);
            List<String> lastTimeSlots = Arrays.asList(wholeDayTimeSlots.toString().split(SysConstant.Punctuation.COMMA));
            if(lastTimeSlots.size()>0){
                planRecord.setTname(lastTimeSlots.get(0));
            }
        }else {
            logger.info(".............选择具体时间段............");
            int existPlanRecord = planRecordDao.queryIfExist(planRecord);
            if(existPlanRecord>0){
                logger.error("-------||-----该排课时间有冲突-----||---");
                return -1;
            }
        }
        return 0;
    }
}
