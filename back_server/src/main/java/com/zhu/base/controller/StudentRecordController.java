package com.zhu.base.controller;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.entity.Classes;
import com.zhu.base.entity.StudentRecord;
import com.zhu.base.entity.Term;
import com.zhu.base.service.ClassesService;
import com.zhu.base.service.StudentRecordService;
import com.zhu.base.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 学籍管理
 * @author yangli
 * @date 17:11 2019/1/14
 */
@RestController
@RequestMapping("/studentrecord")
public class StudentRecordController {

    @Autowired
    private ClassesService classesService;

    @Autowired
    private StudentRecordService recordService;

    @Autowired
    private TermService termService;

    /**
      * 查询学期列表
      * @author yangli
      * @date 2019/1/14
      */
    @RequestMapping(value = "termlist",method = RequestMethod.GET)
    public Map termlist(){
        List<Term> list = termService.queryTermList();
        return ResultUtils.success(list);
    }

    /**
      * 查询班级列表
      * @author yangli
      * @date 2019/1/14
      */
    @RequestMapping(value = "classlist",method = RequestMethod.GET)
    public Map getClasslist(){
        List<Classes> list = classesService.getList();
        return ResultUtils.success(list);
    }

    /**
     * 根据学生列表
     * @author yangli
     * @date 2019/1/15
     * @return list
     */
    @RequestMapping(value = "getlist",method = RequestMethod.GET)
    public Map getStulist(){
        List<Term> list1 = termService.queryTermList();
        List<Classes> list2 = classesService.getList();
        PageInfo<StudentRecord> pageInfo = new PageInfo<>(recordService.getList(list1.get(0).getId(),list2.get(0).getId()));
        return ResultUtils.success(pageInfo);
    }

    /**
      * 根据学期和班级查询学生列表
      * @author yangli
      * @date 2019/1/15
      * @param termid,classid
      * @return list
      */
    @RequestMapping(value = "stulist",method = RequestMethod.GET)
    public Map getStulist(Integer termid,Integer classid){
        PageInfo<StudentRecord> pageInfo = new PageInfo<>(recordService.getList(termid,classid));
        return ResultUtils.success(pageInfo);
    }



}
