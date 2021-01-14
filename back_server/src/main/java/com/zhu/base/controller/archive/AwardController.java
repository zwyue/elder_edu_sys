package com.zhu.base.controller.archive;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.common.TimeUtil;
import com.zhu.base.entity.Award;
import com.zhu.base.entity.Teacher;
import com.zhu.base.service.AwardServie;
import com.zhu.base.util.DocUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 获奖情况管理
 * @author yangli
 * @date 2018/12/25
 */
@CrossOrigin
@RestController
@RequestMapping("/award")
public class AwardController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AwardController.class);

    @Autowired
    private AwardServie awardServie;

    /**
     * 获奖情况列表
     * @author yangli
     * @date 2018/11/28 14:10
     */
    @RequestMapping(value = "list" ,method = RequestMethod.GET)
    public Map getList(){
        PageInfo<Award> pageInfo = new PageInfo<>(awardServie.getList());
        return ResultUtils.success(pageInfo);
    }

    /**
     * 获奖情况新增保存
     * @author yangli
     * @date 2018/11/28 14:10
     */
    @RequestMapping(value = "save" ,method = RequestMethod.POST)
    @ResponseBody
    public Map save(Award award, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        award.setUserid(teacher.getId());
        award.setUsername(teacher.getTname());
        awardServie.insert(award);
        return ResultUtils.success("保存成功");
    }

    /**
     * 获奖情况修改
     * @author yangli
     * @date 2018/11/28 14:10
     */
    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    @ResponseBody
    public Map update(Award award,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        award.setUserid(teacher.getId());
        award.setUsername(teacher.getTname());
        awardServie.updateByPrimaryKey(award);
        return ResultUtils.success("保存成功");
    }

    /**
     * 获奖情况查询
     * @author yangli
     * @date 2018/11/28 14:10
     */
    @RequestMapping(value = "query" ,method = RequestMethod.GET)
    public Map query(Integer id){
        Award award=awardServie.selectByPrimaryKey(id);
        return ResultUtils.success(award);
    }

    /**
     * 获奖情况删除
     * @author yangli
     * @date 2018/11/28 14:10
     */
    @RequestMapping(value = "delete" ,method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = awardServie.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除成功");
        }else {
            return ResultUtils.error("删除失败");
        }
    }

    /**
     * 根据标题查询获奖情况
     * @author yangli
     * @date 2019/1/3
     */
    @RequestMapping(value = "getTitle")
    public Map getTitle(String title){
        PageInfo<Award> pageInfo;
        if(title!=null){
            pageInfo = new PageInfo<>(awardServie.getTitleList(title));
        }else{
            pageInfo = new PageInfo<>(awardServie.getList());
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 导出获奖情况并下载word文档
     * @author yangli
     * @date 2019/1/9
     */
    @RequestMapping(value = "export")
    public void getDoc(HttpServletRequest request, HttpServletResponse response, Integer id) {
        String names="award.xml";
        Award award=awardServie.selectByPrimaryKey(id);
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("time", TimeUtil.getUserDate(award.getCrttime()));
        dataMap.put("content", award.getContent());
        String newWordName = "获奖情况记录.doc";
        //调用打印word的函数
        DocUtil.download(request, response, newWordName, dataMap,names);
    }
}
