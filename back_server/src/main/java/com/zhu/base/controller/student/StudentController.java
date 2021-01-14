package com.zhu.base.controller.student;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.dto.StuAndStuEnter;
import com.zhu.base.entity.Student;
import com.zhu.base.entity.StudentEnter;
import com.zhu.base.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.List;
import java.util.Map;

import static com.zhu.base.exception.ExceptionEnum.*;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/20 18:34
 *     email        1092478224@qq.com
 *     desc         学生管理
 * </pre>
 */
@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService ;

    /**
     * 获取学生列表
     *
     * @author zwy
     * @date 2018/12/25 9:57
     */
    @RequestMapping("/list")
    @GET
    public Map studentList(String name , String idCard,String termStatus){
        logger.info("===========获取学生信息============");
        List<Student> studentList = studentService.queryAllStudents(name,idCard,termStatus);
        return ResultUtils.success(new PageInfo<>(studentList));
    }

    /**
     * 学生报名信息录入
     *      包括报名信息表、学生信息表、花名册表
     *
     * @author zwy
     * @date 2018/12/21 9:30
     */
    @RequestMapping("/enter")
    @POST
    public Map enterStudentInfo(Student student
                                , StudentEnter studentEnter
                                , HttpSession httpSession){
        logger.info("......学生报名录入(单个)......");
        studentEnter.setTid(getLoginUser(httpSession).getId());
        studentEnter.setTname(getLoginUser(httpSession).getTname());
        return studentService.enterStudentInfo(student,studentEnter,"1");
    }

    @RequestMapping("/add")
    @POST
    public Map addStudentInfo(Student student
            , StudentEnter studentEnter
            , HttpSession httpSession){
        logger.info("......学生新增录入(单个)......");
        studentEnter.setTid(getLoginUser(httpSession).getId());
        studentEnter.setTname(getLoginUser(httpSession).getTname());
        return studentService.enterStudentInfo(student,studentEnter,"2");
    }

    /**
     * 更新学生基本信息
     *
     *
     * @author zwy
     * @date 2018/12/25 10:10
     */
    @RequestMapping("/update-base")
    @GET
    public Map updateStudent(Student student){
        logger.info("============更新学生信息===========");
        Integer isDelete = studentService.updateStudentInfo(student) ;
        if(isDelete>0){
            return ResultUtils.success(SUCCESS.errorCode,SUCCESS.errorMessage);
        }else {
            return ResultUtils.error(UPDATE_FAILED.errorCode,UPDATE_FAILED.errorMessage);
        }
    }

    /**
     * 更新学生报名信息
     *
     * @author zwy
     * @date 2018/12/27 9:46
     */
    @RequestMapping("/update-enter")
    @POST
    public Map updateStudent(StudentEnter studentEnter){
        return studentService.updateEnterInfo(studentEnter);
    }

    /**
     * 学生信息删除
     *
     * @author zwy
     * @date 2018/12/25 11:37
     */
    @RequestMapping("/delete")
    @DELETE
    public Map deleteStudent(Integer stuId){
        logger.info("============删除信息============");
        Integer ifDelete = studentService.deleteStuInfo(stuId);
        if(ifDelete>0) {
            return ResultUtils.success(SUCCESS);
        }else {
            return ResultUtils.error(DELETE_FAILED.errorCode,DELETE_FAILED.errorMessage);
        }
    }

    /**
     * 学生信息详情
     *
     * @author zwy
     * @date 2019/2/14 13:52
     */
    @RequestMapping("/all-detail")
    @GET
    public Map studentInfo(Integer id,String idCard){
        logger.info("............获取学生详情.........id={},idCard:{}",id,idCard);
        Map studentInfo = studentService.queryStuAllInfo(id,idCard);
        return ResultUtils.success(studentInfo) ;
    }

    /**
     * 更新基本信息跟报名信息
     *
     * @author zwy
     * @date 2019/3/1 11:13
     */
    @RequestMapping("/update")
    @POST
    public Map update(StuAndStuEnter stuAndStuEnter){
        //学生修改1，报名修改2
        studentService.update(stuAndStuEnter,"1");
        return ResultUtils.success();
    }
}
