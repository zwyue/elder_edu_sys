package com.zrtjoa.controller.studymeet;

import com.github.pagehelper.PageInfo;
import com.zrtjoa.common.BaseController;
import com.zrtjoa.common.ResultUtils;
import com.zrtjoa.entity.Student;
import com.zrtjoa.entity.StudyMeet;
import com.zrtjoa.service.StudentService;
import com.zrtjoa.service.StudyMeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 学委会管理
 * @author yangli
 * @date 2018/12/25
 */
@CrossOrigin
@RestController
@RequestMapping("/studymeet")
public class StudyMeetController extends BaseController {

    @Autowired
    private StudyMeetService studyMeetService;

    @Autowired
    private StudentService studentService;

    /**
     * 查询学委会学生列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(){
        PageInfo<StudyMeet> pageInfo = new PageInfo<>(studyMeetService.getList());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 查询未加入学委会的学生列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "slist",method = RequestMethod.GET)
    public Map slist(){
        PageInfo<Student> pageInfo = new PageInfo<>(studentService.getStudentList());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 根据学生姓名查询未加入学委会的学生列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "getName",method = RequestMethod.GET)
    public Map getNamelist(String name){
        PageInfo<Student> pageInfo;
        if(name!=null){
            pageInfo = new PageInfo<>(studentService.getNameList(name));
        }else{
            pageInfo = new PageInfo<>(studentService.getStudentList());
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 删除学委会学生
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = studyMeetService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else{
            return ResultUtils.success("删除成功");
        }
    }

    /**
     * 学委会成员新增
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(Integer sid){
        Student student = studentService.selectByPrimaryKey(sid);
        StudyMeet studyMeet = new StudyMeet();
        studyMeet.setSid(student.getId());
        studyMeet.setSname(student.getStuname());
        studyMeet.setClassid(student.getClassid());
        studyMeet.setClassname(student.getClassname());
        studyMeet.setPhone(student.getPhone());
        studyMeet.setEmergency(student.getEmergency());
        studyMeet.setBusiness("学生");
        studyMeetService.insert(studyMeet);
        return ResultUtils.success("保存成功");
    }

    /**
     * 学委会查询
     * @author yangli
     * @date 2019/2/14
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    @ResponseBody
    public Map query(String sname){
        PageInfo<StudyMeet> pageInfo;
        if(sname!=null){
            pageInfo = new PageInfo<>(studyMeetService.getSnameList(sname));
        }else{
            pageInfo = new PageInfo<>(studyMeetService.getList());
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 学委会添加职务
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "addduties",method = RequestMethod.POST)
    @ResponseBody
    public Map addduties(StudyMeet studyMeet){
        studyMeetService.updateDuties(studyMeet);
        return ResultUtils.success("添加成功");
    }

}
