package com.zrtjoa.service;

import com.zrtjoa.entity.Teacher;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * TeacherService interface
 *
 * @author zwy
 * @date 2018/11/28 13:13
 */
public interface TeacherService {

    /**
     * 根据教师编号查询教师
     *
     * @author zwy
     * @date 2018/11/28 13:16
     * @param tnumber 教师编号
     * @return Teacher
     */
    Teacher queryTeacherByNumber(String tnumber);

    /**
     * 根据id查询人员信息
     *
     * @author zwy
     * @date 2018/12/3 10:02
     * @param userId 教师id
     * @return int
     */
    Teacher queryTeacherById(Integer userId);

    /**
     * 更新用户信息
     *
     * @author zwy
     * @date 2018/12/3 11:37
     * @param teacher 教师
     * @return int
     */
    Integer updateTeacher(Teacher teacher);

    /**
     * 查询全部教职工(符合条件的)
     *
     * @author zwy
     * @date 2018/12/11 16:40
     * @param name 姓名
     * @param idCard 身份证号
     * @return list
     */
    List<Teacher> queryAllTeacher(String name, String idCard);

    /**
     * 根据角色id模糊查询用户
     *
     * @author zwy
     * @date 2018/12/11 17:58
     * @param roleId 角色id
     * @return list
     */
    List<Teacher> queryTeacherByRoleId(Integer roleId);

    /**
     * 录入教师信息
     *
     * @author zwy
     * @date 2018/12/20 14:08
     * @param teacher 教师信息
     * @return int
     */
    Integer enterTeacher(Teacher teacher) ;

    /**
     * 删除人员信息
     *
     * @author zwy
     * @date 2018/12/20 18:10
     * @param id 人员id
     * @return int
     */
    Integer deleteTeacher(Integer id);

    /**
     * 根据专业查询教师列表
     * @author yangli
     * @date 2019/1/3
     * @param majorid 专业id
     * @return list
     */
    List<Teacher> getteaList(Integer majorid);

    /**
     *  教师绑定班级
     *
     * @author zwy
     * @date 2019/2/27 14:59
     * @param teacherId 教师id
     * @param classIds 班级id
     * @param classNames 班级名称
     * @return int
     */
    Integer teacherBindClass(Integer teacherId, String classIds,String classNames);

    /**
     * 根据教师id查询绑定班级
     *
     * @author zwy
     * @date 2019/2/28 9:53
     * @param teacherId 教师id
     * @return string
     */
    Map<String,Object> ifHasBindCls(Integer teacherId);

    /**
     * 更新密码
     *
     * @author zwy
     * @date 2019/3/14 10:05
     */
    Map updatePsw(Teacher teacher, String newPsw,HttpSession httpSession);
}
