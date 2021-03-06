package com.zhu.base.controller.log;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.entity.Teacher;
import com.zhu.base.entity.WorkNotes;
import com.zhu.base.service.WorkNotesService;
import com.zhu.base.util.DocUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志管理-班主任工作手记管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/worknotes")
public class WorkNotesController extends BaseController {

    @Autowired
    private WorkNotesService workNotesService;

    /**
     * 日志管理-班主任工作手记管理列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        PageInfo<WorkNotes> pageInfo;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            pageInfo = new PageInfo<>(workNotesService.getLists());
        }else{
            pageInfo = new PageInfo<>(workNotesService.getList(teacher.getId()));
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-班主任手记按标题查询
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "getTitle")
    public Map getTitle(String title,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        PageInfo<WorkNotes> pageInfo;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            pageInfo = new PageInfo<>(workNotesService.getTitleList(title));
        }else{
            pageInfo = new PageInfo<>(workNotesService.getTitleLists(teacher.getId(),title));
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-班主任工作手记新增保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(WorkNotes record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        workNotesService.insert(record);
        return ResultUtils.success("保存成功");
    }

    /**
     * 日志管理-班主任工作手记修改页面
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id){
        WorkNotes workNotes = workNotesService.selectByPrimaryKey(id);
        return ResultUtils.success(workNotes);
    }

    /**
     * 日志管理-班主任工作手记修改保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map update(WorkNotes workNotes){
        workNotesService.updateByPrimaryKey(workNotes);
        return ResultUtils.success("修改成功");
    }

    /**
     * 日志管理-班主任工作手记删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = workNotesService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else{
            return ResultUtils.success("删除成功");
        }
    }

    /**
     * 日志管理-班主任工作手记导出
     * @author yangli
     * @date 2019/1/9
     */
    @RequestMapping(value = "export")
    public void getDoc(HttpServletRequest request, HttpServletResponse response, Integer id) {
        String names="worknotes.xml";
        WorkNotes workNotes=workNotesService.selectByPrimaryKey(id);
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("content", workNotes.getContent());
        String newWordName = "班主任工作手记记录.doc";
        //调用打印word的函数
        DocUtil.download(request, response, newWordName, dataMap,names);
    }
}
