package com.zhu.base.controller.archive;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.common.TimeUtil;
import com.zhu.base.entity.Resource;
import com.zhu.base.entity.Teacher;
import com.zhu.base.service.ResourceService;
import com.zhu.base.util.DocUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * ResourceController class
 *
 * @author zwy
 * @date 2018/11/28 15:43
 */
@CrossOrigin
@RestController
@RequestMapping("resource")
public class ResourceController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class) ;

    @Autowired
    private ResourceService resourceService ;

    /**
     * 保存资源建设
     *
     * @author zwy
     * @date 2018/11/28 16:10
     */
    @RequestMapping(value = "saveResource",method = RequestMethod.POST)
    @ResponseBody
    public Map saveResouce(Resource resource, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        resource.setUserid(teacher.getId());
        resource.setUsername(teacher.getTname());
        resourceService.saveResouce(resource);
        return ResultUtils.success("保存成功");
    }

    /**
     * 获取资源建设列表
     *
     * @author zwy
     * @date 2018/11/28 16:32
     */
    @RequestMapping("list")
    public Map list(){
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceService.queryResourceList());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 档案查询
     *
     * @author zwy
     * @date 2018/11/28 14:09
     */
    @RequestMapping(value = "queryResourceById" ,method = RequestMethod.GET)
    public Map queryMemorabilia(Integer id){
        logger.info("========查询id为{}的资源========",id);
        Resource resource = resourceService.queryResourceById(id);
        return ResultUtils.success(resource);
    }

    /**
     * 资源建设更新
     *
     * @author zwy
     * @date 2018/11/28 14:09
     */
    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    @ResponseBody
    public Map updateResource(Resource resource){
        logger.info("=========资源建设更新=========");
        resourceService.updateResource(resource);
        return ResultUtils.success("更新成功") ;
    }

    /**
     * 删除资源建设
     *
     * @author zwy
     * @date 2018/11/28 17:37
     */
    @RequestMapping(value = "delete" ,method = RequestMethod.GET)
    public Map deleteMemorabilia(Integer id){
        /*logger.info("======删除{}条资源建设======",idList.size());
        int delete = resourceService.deleteResouece(idList);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.error("删除成功");
        }*/
        Integer delete = resourceService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.error("删除成功");
        }
    }

    /**
     * 根据标题查询资源建设列表
     * @author yangli
     * @date 2019/1/3
     * @param
     * @return
     */
    @RequestMapping(value = "getTitle")
    public Map getTitle(String title,String category){
        Map map = new HashMap();
        map.put("title", title);
        map.put("category", category);
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceService.getTitleList(map));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 导出资源建设并下载word文档
     * @author yangli
     * @date 2019/1/9
     */
    @RequestMapping(value = "export")
    public void getDoc(HttpServletRequest request, HttpServletResponse response, Integer id) {
        String names="resource.xml";
        Resource resource=resourceService.queryResourceById(id);
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("time", TimeUtil.getUserDate(resource.getCrttime()));
        dataMap.put("content", resource.getContent());
        String newWordName = "资源建设记录.doc";
        //调用打印word的函数
        DocUtil.download(request, response, newWordName, dataMap,names);
    }
}
