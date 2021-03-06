package com.zhu.base.controller.log;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.common.TimeUtil;
import com.zhu.base.entity.ActivityRecord;
import com.zhu.base.entity.Teacher;
import com.zhu.base.service.ActivityRecordService;
import com.zhu.base.util.DocUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志管理-校园活动记录管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/activityrecord")
public class ActivityReordController extends BaseController {

    @Autowired
    private ActivityRecordService recordService;

    /**
     * 日志管理-校园活动记录列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(){
        PageInfo<ActivityRecord> pageInfo = new PageInfo<>(recordService.getList());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-学校活动记录按标题查询
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "getTitle")
    public Map getTitle(String title){
        PageInfo<ActivityRecord> pageInfo = new PageInfo<>(recordService.getTitleList(title));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-校园活动记录新增保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(ActivityRecord record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        recordService.insert(record);
        return ResultUtils.success("保存成功");
    }

    /**
     * 日志管理-校园活动记录修改页面
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id){
        ActivityRecord record = recordService.selectByPrimaryKey(id);
        return ResultUtils.success(record);
    }

    /**
     * 日志管理-校园活动记录修改提交
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map update(ActivityRecord record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        recordService.updateByPrimaryKey(record);
        return ResultUtils.success("保存成功");
    }

    /**
     * 日志管理-校园活动记录删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = recordService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.error("删除成功");
        }
    }

    /**
     * 导出学院活动并下载word文档
     * @author yangli
     * @date 2019/1/9
     */
    @RequestMapping(value = "export")
    public void getDoc(HttpServletRequest request, HttpServletResponse response, Integer id) {
        String names="activitiesrecord.xml";
        ActivityRecord record=recordService.selectByPrimaryKey(id);
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("time", TimeUtil.getUserDate(record.getRecordtime()));
        dataMap.put("content", record.getContent());
        String newWordName = "学院活动记录.doc";
        //调用打印word的函数
        DocUtil.download(request, response, newWordName, dataMap,names);
    }
}
