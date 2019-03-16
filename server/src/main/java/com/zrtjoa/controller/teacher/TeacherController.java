package com.zrtjoa.controller.teacher;

import com.github.pagehelper.PageInfo;
import com.zrtjoa.common.BaseController;
import com.zrtjoa.common.ResultUtils;
import com.zrtjoa.entity.Teacher;
import com.zrtjoa.service.TeacherService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.time.LocalTime;
import java.util.Map;

import static com.zrtjoa.constant.SysConstant.Punctuation.COMMA;
import static com.zrtjoa.exception.ExceptionEnum.*;

/**
 * copyright    <a href="http://www.qaqavr.com"/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/20 13:47
 *     email        1092478224@qq.com
 *     desc         教师管理
 * </pre>
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService ;

    /**
     * 获取教师列表
     *
     * @author zwy
     * @date 2018/12/20 13:58
     */
    @RequestMapping("/list")
    @GET
    public Map teacherInfo(String name ,String idCard){
        logger.info("......查询老师开始:{}......", LocalTime.now());
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherService.queryAllTeacher(name,idCard));
        logger.info("......查询老师结束:{}......", LocalTime.now());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 根据专业id查询教师
     *
     * @author zwy
     * @date 2019/2/22 14:01
     */
    @RequestMapping("/query-prfs-teacher")
    @GET
    public Map queryTeacherByPrfsId(Integer professionId){
        return ResultUtils.success(teacherService.getteaList(professionId)) ;
    }

    /**
     * 录入教师人员信息
     *
     * @author zwy
     * @date 2018/12/20 18:07
     */
    @RequestMapping("/enter")
    @POST
    public Map enterTeacherInfo(Teacher teacher){
        logger.info(".......教师基本信息录入..姓名：{}......",teacher.getTname());
        Integer ifEnter = teacherService.enterTeacher(teacher);
        if(ifEnter>0){
            return ResultUtils.error(TEACHER_EXIST.errorCode,TEACHER_EXIST.errorMessage);
        }
        return ResultUtils.success() ;
    }

    /**
     * 选择负责班级
     *
     * @author zwy
     * @date 2019/2/26 16:28
     */
    @RequestMapping("/charge-cls")
    @POST
    public Map chargeCls(Integer teacherId,String classIds,String classNames){
        logger.info("......绑定班级......教师：{}......",teacherId);
        teacherService.teacherBindClass(teacherId
                ,classIds.substring(0,classIds.lastIndexOf(COMMA))
                ,classNames.substring(0,classNames.lastIndexOf(COMMA)));
        return ResultUtils.success() ;
    }

    /**
     * 删除人员信息 todo - 批量删除
     *
     * @author zwy
     * @date 2018/12/20 18:08
     */
    @RequestMapping("/delete")
    public Map deleteTeacher(Integer id){
        Integer ifDelete = teacherService.deleteTeacher(id);
        if(ifDelete>0){
            return ResultUtils.success(SUCCESS.errorCode,SUCCESS.errorMessage);
        }else {
            return ResultUtils.error(DELETE_FAILED.errorCode,DELETE_FAILED.errorMessage);
        }
    }

    /**
     * 更新教师信息
     *
     * @author zwy
     * @date 2018/12/20 18:30
     */
    @RequestMapping("/update")
    @POST
    public Map updateTeacher(Teacher teacher){
        Integer ifUpdate = teacherService.updateTeacher(teacher);
        if(ifUpdate>0){
            return ResultUtils.success(SUCCESS.errorCode,SUCCESS.errorMessage);
        }else {
            return ResultUtils.error(UPDATE_FAILED.errorCode,UPDATE_FAILED.errorMessage);
        }
    }

    /**
     * 查看教师详情
     *
     * @author zwy
     * @date 2019/2/11 11:34
     */
    @RequestMapping("/detail")
    @GET
    public Map teacherDetail(Integer id){
        Teacher teacher = teacherService.queryTeacherById(id);
        return ResultUtils.success(teacher) ;
    }

    /**
     * 查询班主任
     *
     * @author zwy
     * @date 2019/2/22 15:09
     */
    @RequestMapping("/queryteacherbyroleid")
    @GET
    public Map headTeacher(Integer roleId){
        return ResultUtils.success(teacherService.queryTeacherByRoleId(roleId)) ;
    }

    @RequestMapping("/queryteacherbyroleids")
    @GET
    public Map headTeachers(Integer roleId){
        roleId=2;
        return ResultUtils.success(teacherService.queryTeacherByRoleId(roleId)) ;
    }

    /**
     * 查询教师是否绑定班级
     *
     * @author zwy
     * @date 2019/2/28 9:49
     */
    @RequestMapping("/ifhasbindcls")
    @GET
    public Map ifHasBindCls(Integer teacherId){
        Map<String,Object> bindCls = teacherService.ifHasBindCls(teacherId);
        if(bindCls!=null){
            return ResultUtils.success(bindCls);
        }
        return ResultUtils.error();
    }

    /**
     * 修改密码
     *
     * @author zwy
     * @date 2019/3/14 9:42
     */
    @RequestMapping("/update-psw")
    @POST
    public Map updatePsw(HttpSession httpSession, String newPsw){
        return teacherService.updatePsw(getLoginUser(httpSession),newPsw,httpSession);
    }
}
