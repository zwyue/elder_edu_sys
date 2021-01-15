package com.zhu.base.controller;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.entity.*;
import com.zhu.base.service.*;
import com.zhu.base.util.DocUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学管理
 * @author yangli
 * @date 11:40 2018/12/27
 */
@RestController
@RequestMapping("/teachmanager")
public class TeachManagerController extends BaseController {

    @Autowired
    private TeachManagerService teachManagerService;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProfessionService professionService;


    /**
     * 查询教学管理列表
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(String types){
        PageInfo<TeachManager> pageInfo=new PageInfo<TeachManager>(teachManagerService.getList(types));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 根据姓名查询
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "getNameList",method = RequestMethod.GET)
    public Map getNameList(String name,String type){
        PageInfo<TeachManager> pageInfo=new PageInfo<TeachManager>(teachManagerService.getNameList(name,type));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 根据教师id查询班级列表
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "classlist",method = RequestMethod.GET)
    public Map classlist(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        List<Classes> list = classesService.getTeaClasslist(teacher.getId());
        return ResultUtils.success(list);
    }

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
     * 根据专业id查询所有教师列表
     * @author yangli
     * @date 2019/02/20
     */
    @RequestMapping(value = "teacherlist",method = RequestMethod.GET)
    public Map teacherlist(Integer majorid){
        List<Teacher> list = teacherService.getteaList(majorid);
        return ResultUtils.success(list);
    }

    /**
     * 查询所有教师列表
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "tealist",method = RequestMethod.GET)
    public Map tealist(String name,String idCard){
        List<Teacher> list = teacherService.queryAllTeacher(name, idCard);
        return ResultUtils.success(list);
    }

    /**
     * 教学管理保存
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(TeachManager record,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setAuditorid(teacher.getId());
        record.setAuditorname(teacher.getTname());
        teachManagerService.insert(record);
        return ResultUtils.success("保存成功");
    }

    /**
     * 查看一条请假记录
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id){
        TeachManager record = teachManagerService.selectByPrimaryKey(id);
        return ResultUtils.success(record);
    }

    /**
     * 导出教学管理并下载word文档
     * @author yangli
     * @date 2019/1/9
     */
    @RequestMapping(value = "export")
    public void getDoc(HttpServletRequest request, HttpServletResponse response, Integer id) {
        TeachManager teachManager=teachManagerService.selectByPrimaryKey(id);
        String names="leave.xml";
        String newWordName,titles;
        if ("0".equals(teachManager.getType())){
            titles="请假";
            newWordName = "南开区老年大学教师请假申请单.doc";
        }else if("1".equals(teachManager.getType())){
            titles="停课";
            newWordName = "南开区老年大学教师停课申请单.doc";
        }else{
            titles="倒课";
            newWordName = "南开区老年大学教师倒课申请单.doc";
        }
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("titles", titles);
        dataMap.put("tname", teachManager.getTname());
        dataMap.put("classname", teachManager.getClassname());
        dataMap.put("leavedate", teachManager.getLeavedate());
        dataMap.put("starttime", teachManager.getStarttime());
        dataMap.put("endtime", teachManager.getEndtime());
        dataMap.put("bkkssj", teachManager.getBkkssj());
        dataMap.put("bkjssj", teachManager.getBkjssj());
        dataMap.put("roomname", teachManager.getRoomname());
        dataMap.put("issure", teachManager.getIssure());
        //调用打印word的函数
        DocUtil.download(request, response, newWordName, dataMap,names);
    }

}
