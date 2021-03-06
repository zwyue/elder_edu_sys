package com.zhu.base.controller.log;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.common.TimeUtil;
import com.zhu.base.entity.AwardRecord;
import com.zhu.base.entity.Teacher;
import com.zhu.base.service.AwardRecordService;
import com.zhu.base.util.DocUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志管理-获奖情况记录
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/awardrecord")
public class AwardRecordController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WorkPlanController.class);

    @Autowired
    private AwardRecordService awardRecordService;

    /**
     * 日志管理-获奖情况记录列表
     * @author yangli
     * @date 2018/11/28 14:10
     */
    @RequestMapping(value = "list" ,method = RequestMethod.GET)
    public Map getList(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        PageInfo<AwardRecord> pageInfo;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            pageInfo = new PageInfo<>(awardRecordService.getLists());
        }else{
            pageInfo = new PageInfo<>(awardRecordService.getList(teacher.getId()));
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-获奖情况记录按标题查询
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "getTitle",method = RequestMethod.GET)
    public Map getTitle(String title,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        PageInfo<AwardRecord> pageInfo;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            pageInfo = new PageInfo<>(awardRecordService.getTitleList(title));
        }else{
            pageInfo = new PageInfo<>(awardRecordService.getTitleLists(teacher.getId(),title));
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-获奖情记录保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(AwardRecord record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        awardRecordService.insert(record);
        return ResultUtils.success("保存成功");
    }

    /**
     * 日志管理-获奖情记录修改页面
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id){
        AwardRecord record = awardRecordService.selectByPrimaryKey(id);
        return ResultUtils.success(record);
    }

    /**
     * 日志管理-获奖情况记录修改
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map update(AwardRecord record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        awardRecordService.updateByPrimaryKey(record);
        return ResultUtils.success("修改成功");
    }

    /**
     * 日志管理-获奖情况记录删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = awardRecordService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else{
            return ResultUtils.success("删除成功");
        }
    }

    /**
     * 导出获奖情况记录并下载word文档
     * @author yangli
     * @date 2019/1/9
     */
    @RequestMapping(value = "export")
    public void getDoc(HttpServletRequest request, HttpServletResponse response, Integer id) {
        String names="awardrecord.xml";
        AwardRecord record=awardRecordService.selectByPrimaryKey(id);
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("time", TimeUtil.getUserDate(record.getRecordtime()));
        dataMap.put("content", record.getContent());
        String newWordName = "学院获奖记录.doc";
        //调用打印word的函数
        DocUtil.download(request, response, newWordName, dataMap,names);
    }
}
