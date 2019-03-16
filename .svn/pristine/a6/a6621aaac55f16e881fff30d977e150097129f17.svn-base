package com.zrtjoa.service.impl;

import com.zrtjoa.annotation.PagingQuery;
import com.zrtjoa.dao.ClassrecordDao;
import com.zrtjoa.dao.ClassroomDao;
import com.zrtjoa.dao.ClasstypeDao;
import com.zrtjoa.dao.CourseTimeDao;
import com.zrtjoa.entity.*;
import com.zrtjoa.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/3 15:56
 *     email        1092478224@qq.com
 *     desc
 * </pre>
 */
@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    @Autowired
    private ClassroomDao classroomDao ;

    @Autowired
    private ClasstypeDao classtypeDao ;

    @Autowired
    private ClassrecordDao classrecordDao ;

    @Autowired
    private CourseTimeDao courseTimeDao ;

    @Override
    public Integer addNewClassRoom(Classroom classroom) {
        return classroomDao.insert(classroom);
    }

    @Override
    public Integer addNewCategory(Classtype classtype) {
        return classtypeDao.insert(classtype);
    }

    @Override
    @PagingQuery
    public List<Classroom> queryClassroomList(Classroom classroom) {
        return classroomDao.queryClassroomList(classroom);
    }

    @Override
    @PagingQuery
    public List<Classtype> queryClassTypeList() {
        return classtypeDao.queryClassTypeList();
    }

    @Override
    public Integer updateClassRoomType(Classtype classtype) {
        return classtypeDao.updateByPrimaryKey(classtype);
    }

    @Override
    public Integer updateClassRoom(Classroom classroom) {
        return classroomDao.updateByPrimaryKeySelective(classroom);
    }

    @Override
    @PagingQuery
    public List<Classrecord> clsRmUsageHistory(Integer roomid) {
        return classrecordDao.clsRmUsageHistory(roomid);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return classroomDao.deleteByPrimaryKey(id);
    }

    @Override
    public Classroom selectByPrimaryKey(Integer id) {
        return classroomDao.selectByPrimaryKey(id);
    }

    @Override
    @PagingQuery
    public List<Classroom> queryVacantClsRoom(Integer week, String date, String starttime, String endtime) {
        //获取星期
        week = week == null ? LocalDate.now().getDayOfWeek().getValue() : week ;
        starttime = starttime == null ? LocalDate.now().toString() + " 00:00:00": starttime ;
        endtime = endtime == null ? LocalDate.now().toString() + " 23:59:59" : endtime ;
        Map<String,Object> map = new HashMap();
        map.put("week",week);
        map.put("date",date);
        map.put("starttime",starttime);
        map.put("endtime",endtime);
        List<Classroom> classrooms = classroomDao.queryVacantClsRoom(map);
        return classrooms;
    }

    @Override
    public List<Classroom> getClassroomList(String classroom, String catename) {
        Map map = new HashMap();
        map.put("classroom",classroom);
        map.put("catename",catename);
        List<Classroom> classrooms = classroomDao.getClassroomList(map);
        return classrooms;
    }

    @Override
    public List<CourseTime> queryAllTimeSlot() {
        return courseTimeDao.queryAllTimeSlot();
    }

    @Override
    public Classtype selectByPrimaryKeys(Integer id) {
        return classtypeDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer deleteByPrimaryKeys(Integer id) {
        return classtypeDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<Classtype> getCateList(String catename) {
        return classtypeDao.getCateList(catename);
    }


}
