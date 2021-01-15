package com.zhu.base.controller;

import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.entity.*;
import com.zhu.base.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * @author yangli
 * @date 13:23 2019/2/27
 */
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {

    @Autowired
    private ClassesService classesService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProfessionService professionService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询所有类别列表
     * @author yangli
     * @date 2019/02/20
     */
    @RequestMapping(value = "catelist",method = RequestMethod.GET)
    public Map catelist(){
        List<Category> list = categoryService.getcatelist();
        return ResultUtils.success(list);
    }

    /**
     * 根据类别id查询所有专业列表
     * @author yangli
     * @date 2019/02/20
     */
    @RequestMapping(value = "prolist",method = RequestMethod.GET)
    public Map prolist(Integer cateid){
        List<Profession> list = professionService.getprolist(cateid);
        return ResultUtils.success(list);
    }

    /**
     * 根据专业id查询班级
     * @author yangli
     * @date 2019/2/18 11:21
     */
    @RequestMapping(value="classlist",method = RequestMethod.GET)
    public Map queryClsByPrfs(Integer proid){
        List<Classes> clses = classesService.getCList(proid);
        return ResultUtils.success(clses) ;
    }

    /**
     * 根据班级id查询学生
     * @author yangli
     * @date 2019/2/18 11:21
     */
    @RequestMapping(value="studentlist",method = RequestMethod.GET)
    public Map getStudentList(Integer classid){
        List<Student> students = studentService.getSList(classid);
        return ResultUtils.success(students) ;
    }

    /**
     * 判断是否为班主任
     * @author yangli
     * @date 2019/2/26
     * @return list
     */
    @RequestMapping(value = "judge",method = RequestMethod.GET)
    public Map judgeheader(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        Object judge;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            judge="0";
        }else{
            judge="1";
        }
        return ResultUtils.success(judge);
    }

    /**
     * 班级列表
     * @author yangli
     * @date 2019/2/26
     * @return list
     */
    @RequestMapping(value = "getClass",method = RequestMethod.GET)
    public Map getClass(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        List<Classes> list;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            list = classesService.getList();
        }else{
            list = classesService.getTeaClasslists(teacher.getId());
        }
        return ResultUtils.success(list);
    }

    /**
      * 根据专业查询教师列表
      * @author yangli
      * @date 2019/3/15
      * @param proid
      * @return list
      */
    @RequestMapping(value = "/getTealist",method = RequestMethod.GET)
    public Map getTealist(Integer proid){
        List<Teacher> teachers = teacherService.getteaList(proid);
        return ResultUtils.success(teachers);
    }
}
