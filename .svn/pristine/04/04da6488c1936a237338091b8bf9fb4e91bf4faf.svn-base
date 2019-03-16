package com.zrtjoa.controller.log;

import com.github.pagehelper.PageInfo;
import com.zrtjoa.common.BaseController;
import com.zrtjoa.common.ResultUtils;
import com.zrtjoa.common.TimeUtil;
import com.zrtjoa.entity.Teacher;
import com.zrtjoa.entity.WorkSummary;
import com.zrtjoa.service.WorkSummaryService;
import com.zrtjoa.util.DocUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志管理-班主任工作总结管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/worksummary")
public class WorkSummaryComtroller extends BaseController {

    @Autowired
    private WorkSummaryService workSummaryService;

    /**
     * 日志管理-班主任工作总结列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        PageInfo<WorkSummary> pageInfo;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            pageInfo = new PageInfo<>(workSummaryService.getLists());
        }else{
            pageInfo = new PageInfo<>(workSummaryService.getList(teacher.getId()));
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-班主任工作总结按标题查询
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "getTitle")
    public Map getTitle(String title,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        PageInfo<WorkSummary> pageInfo;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            pageInfo = new PageInfo<>(workSummaryService.getTitleList(title));
        }else{
            pageInfo = new PageInfo<>(workSummaryService.getTitleLists(teacher.getId(),title));
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-班主任工作总结新增保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(WorkSummary record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        workSummaryService.insert(record);
        return ResultUtils.success("保存成功");
    }

    /**
     * 日志管理-班主任工作总结修改保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map update(WorkSummary record,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        workSummaryService.updateByPrimaryKey(record);
        return ResultUtils.success("修改成功");
    }

    /**
     * 日志管理-班主任工作总结删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = workSummaryService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.error("删除成功");
        }
    }

    /**
     * 查看详情
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public Map detail(Integer id){
        WorkSummary work = workSummaryService.selectByPrimaryKey(id);
        return ResultUtils.success(work);
    }

    /**
     * 日志管理-班主任工作总结导出
     * @author yangli
     * @date 2019/1/9
     */
    @RequestMapping(value = "export")
    public void getDoc(HttpServletRequest request, HttpServletResponse response, Integer id) {
        String names="worksummary.xml";
        String times;
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("M");//可以方便地修改日期格式
        Integer hehe = Integer.parseInt(dateFormat.format(now));
        if(hehe>9 || hehe<2){
            times="下学期";
        }else{
            times="上学期";
        }
        WorkSummary workSummary=workSummaryService.selectByPrimaryKey(id);
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("time", times);
        dataMap.put("content", workSummary.getContent());
        String newWordName = "班主任工作总结记录.doc";
        //调用打印word的函数
        DocUtil.download(request, response, newWordName, dataMap,names);
    }
}
