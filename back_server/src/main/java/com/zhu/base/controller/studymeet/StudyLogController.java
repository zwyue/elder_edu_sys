package com.zhu.base.controller.studymeet;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.entity.StudyLog;
import com.zhu.base.entity.Teacher;
import com.zhu.base.service.StudyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 学委会日志管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/studylog")
public class StudyLogController extends BaseController {

    @Autowired
    private StudyLogService studyLogService;

    /**
     * 学委会日志管理列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map getList(){
        PageInfo<StudyLog> pageInfo=new PageInfo<StudyLog>(studyLogService.getList());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 学委会日志管理新增保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(StudyLog record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        studyLogService.insert(record);
        return ResultUtils.success("保存成功");
    }

    /**
     * 获奖情况修改
     * @author yangli
     * @date 2018/11/28 14:10
     */
    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    @ResponseBody
    public Map update(StudyLog record,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        studyLogService.updateByPrimaryKey(record);
        return ResultUtils.success("保存成功");
    }

    /**
     * 学委会日志管理修改页面
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id){
        StudyLog record = studyLogService.selectByPrimaryKey(id);
        return ResultUtils.success(record);
    }

    /**
     * 学委会日志管理删除
     * @author yangli
     * @date 2018/12/25
     */
    //@ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = studyLogService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败!");
        }else{
            return ResultUtils.success("删除成功");
        }
    }

    /**
     * 根据标题查询获奖情况
     * @author yangli
     * @date 2019/1/3
     */
    @RequestMapping(value = "getTitle")
    public Map getTitle(String title){
        PageInfo<StudyLog> pageInfo;
        if(title!=null){
            pageInfo = new PageInfo<>(studyLogService.getTitleList(title));
        }else{
            pageInfo = new PageInfo<>(studyLogService.getList());
        }
        return ResultUtils.success(pageInfo);
    }
}
