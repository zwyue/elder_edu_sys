package com.zhu.base.controller.log;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.entity.MeetRecord;
import com.zhu.base.entity.Teacher;
import com.zhu.base.service.MeetRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.zhu.base.constant.SysConstant.Punctuation.COMMA;

/**
 * 班会记录管理
 * @author yangli
 * @date 2018/12/26
 */
@RestController
@RequestMapping("/meetrecord")
public class MeetRecordController extends BaseController {

    @Autowired
    private MeetRecordService meetRecordService;

    /**
     * 查询班会记录列表
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        PageInfo<MeetRecord> pageInfo;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            pageInfo = new PageInfo<>(meetRecordService.getLists());
        }else{
            Map map = new HashMap();
            map.put("classids", Arrays.asList(teacher.getClassid().split(COMMA)));
            pageInfo = new PageInfo<>(meetRecordService.getList(map));
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 日志管理-班会记录按标题查询
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "getTitle")
    public Map getclassList(HttpSession httpSession,String content){
        Teacher teacher = getLoginUser(httpSession);
        PageInfo<MeetRecord> pageInfo;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            pageInfo = new PageInfo<>(meetRecordService.getTitleList(content));
        }else{
            Map map = new HashMap();
            map.put("classids", Arrays.asList(teacher.getClassid().split(COMMA)));
            map.put("content", content);
            pageInfo = new PageInfo<>(meetRecordService.getTitleLists(map));
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 保存班会记录
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(MeetRecord record,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        meetRecordService.insert(record);
        return ResultUtils.success("保存成功");
    }

    /**
     * 查询一条班会记录详情
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id){
        MeetRecord record = meetRecordService.selectByPrimaryKey(id);
        return ResultUtils.success(record);
    }

    /**
     * 班会记录修改保存
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Map update(MeetRecord record){
        meetRecordService.updateByPrimaryKey(record);
        return ResultUtils.success("修改成功");
    }

    /**
     * 删除一条班会记录
     * @author yangli
     * @date 2018/12/27
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = meetRecordService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除成功");
        }else{
            return ResultUtils.success("删除失败");
        }
    }

    /**
     * 导出班会记录excel
     *
     * @author zwy
     * @date 2019/1/23 9:17
     */
    @RequestMapping("/exportmeetrcd")
    public void exportMeetRecord(HttpServletResponse response, Integer ... ids){
        meetRecordService.exportMeetRecord(response,ids);
    }
}
