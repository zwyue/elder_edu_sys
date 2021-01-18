package com.zhu.base.controller.archive;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.common.TimeUtil;
import com.zhu.base.entity.Activities;
import com.zhu.base.entity.Teacher;
import com.zhu.base.service.ActivitiesService;
import com.zhu.base.util.DocUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 校园活动管理
 * @author yangli
 * @date 2018/12/25
 */
@CrossOrigin
@RestController
@RequestMapping("/activities")
public class ActivitiesController extends BaseController {

    @Autowired
    private ActivitiesService activitiesService;

    /**
     * 校园活动列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map getList(){
        PageInfo<Activities> pageInfo = new PageInfo<>(activitiesService.getList());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 根据标题查询校园活动列表
     * @author yangli
     * @date 2019/1/3
     * @param title
     * @return list
     */
    @RequestMapping(value = "getTitle")
    public Map getTitle(String title){
        PageInfo<Activities> pageInfo = new PageInfo<>(activitiesService.getTitleList(title));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 校园活动新增保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save" ,method = RequestMethod.POST)
    @ResponseBody
    public Map save(Activities activities, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        activities.setUserid(teacher.getId());
        activities.setUsername(teacher.getTname());
        activitiesService.insert(activities);
        return ResultUtils.success("保存成功");
    }

    /**
     * 校园活动详情
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query" ,method = RequestMethod.GET)
    public Map query(Integer id){
        Activities activities = activitiesService.selectByPrimaryKey(id);
        return ResultUtils.success(activities);
    }

    /**
     * 校园活动修改保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    @ResponseBody
    public Map update(Activities activities, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        activities.setUserid(teacher.getId());
        activities.setUsername(teacher.getTname());
        activitiesService.updateByPrimaryKey(activities);
        if(activitiesService.updateByPrimaryKey(activities)>0){
            return ResultUtils.success("修改成功");
        }else{
            return ResultUtils.error("修改失败");
        }
    }

    /**
     * 校园活动删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete" ,method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = activitiesService.deleteByPrimaryKey(id);
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
        String names="activities.xml";
        Activities activities=activitiesService.selectByPrimaryKey(id);
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("time", TimeUtil.getUserDate(activities.getCrttime()));
        dataMap.put("content", activities.getContent());
        String newWordName = "学院活动记录.doc";
        //调用打印word的函数
        DocUtil.download(request, response, newWordName, dataMap,names);
    }
}
