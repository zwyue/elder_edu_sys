package com.zhu.base.service;

import com.zhu.base.entity.Classrecord;
import com.zhu.base.entity.Classroom;
import com.zhu.base.entity.Classtype;
import com.zhu.base.entity.CourseTime;

import java.util.List;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author zwy
 *     @date 2018/12/3 15:55
 *     email       1092478224@qq.com
 *     desc
 * </pre>
 */
public interface ClassRoomService {

    /**
     * 添加新课程
     *
     * @author zwy
     * @date 2018/12/3 16:31
     * @param classroom 教师信息
     * @return int
     */
    Integer  addNewClassRoom(Classroom classroom);

    /**
     * 添加教室分类
     *
     * @author zwy
     * @date 2018/12/3 18:46
     * @param classtype 教室分类信息
     * @return int
     */
    Integer addNewCategory(Classtype classtype);

    /**
     * 查询教室列表
     *
     * @author zwy
     * @date 2018/12/5 10:54
     * @return classroom list
     */
    List<Classroom> queryClassroomList(Classroom classroom);

    /**
     * 查询教师类别列表
     *
     * @author zwy
     * @date 2018/12/5 11:07
     * @return list
     */
    List<Classtype> queryClassTypeList();

    /**
     * 更新教室类别
     *
     * @author zwy
     * @date 2018/12/5 14:23
     * @return int
     * @param classtype 教室类别
     */
    Integer updateClassRoomType(Classtype classtype);

    /**
     * 更新教室
     *
     * @author zwy
     * @date 2018/12/5 14:28
     * @param classroom 教室信息
     * @return int
     */
    Integer updateClassRoom(Classroom classroom);

    /**
     * 教室使用历史
     *
     * @author zwy
     * @date 2018/12/5 14:42
     * @return list
     */
    List<Classrecord> clsRmUsageHistory(Integer roomid);

    Integer deleteByPrimaryKey(Integer id);

    Classroom selectByPrimaryKey(Integer id);

    /**
     * 查看空闲教室
     *
     * @author zwy
     * @date 2019/2/18 14:24
     * @return clsRoom
     * @param week
     * @param date
     * @param starttime
     * @param endtime
     */
    List<Classroom> queryVacantClsRoom(Integer week, String date, String starttime, String endtime);

    /**
     * 根据教室名称和教室类别
     * @author yangli
     * @date 2019/2/19
     * @param classroom,catename
     * @return list
     */
    List<Classroom> getClassroomList(String classroom, String catename);

    /**
     * 查询全部课时段
     * @author yangli
     * @date 2019/2/19
     * @return list
     */
    List<CourseTime> queryAllTimeSlot();

    /**
     * 查询教室类别详情
     * @author yangli
     * @date 2019/2/19
     * @return list
     */
    Classtype selectByPrimaryKeys(Integer id);

    /**
     * 删除教室类别
     * @author yangli
     * @date 2019/2/19
     * @return list
     */
    Integer deleteByPrimaryKeys(Integer id);

    /**
     * 教室类别标题
     * @author yangli
     * @date 2019/2/19
     * @return list
     */
    List<Classtype> getCateList(String catename);
}
