package com.zhu.base.controller;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.entity.Term;
import com.zhu.base.service.TermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.List;
import java.util.Map;

/**
 * TermController class
 * 学期管理
 *
 * @author zwy
 * @date 2018/11/29 14:46
 */
@RestController
@RequestMapping("/term")
public class TermController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TermController.class);

    @Autowired
    private TermService termService ;

    /**
     * 学期列表
     *
     * @author zwy
     * @date 2019/1/25 14:25
     */
    @RequestMapping("/termlist")
    @GET
    public Map termList(){
        List<Term> terms = termService.queryTermList();
        PageInfo<Term> pageInfo = new PageInfo<>(terms);
        return ResultUtils.success(pageInfo);
    }

    /**
     * 创建新学期
     *
     * @author zwy
     * @date 2018/11/29 15:56
     */
    @RequestMapping("/newterm")
    @POST
    public Map createNewTerm(HttpSession httpSession , Term term){
        logger.info("........创建新学期.......");
        term.setUserid(getLoginUser(httpSession).getId());
        term.setUsername(getLoginUser(httpSession).getTname());
        termService.createNewTerm(term);
        return ResultUtils.success() ;
    }

    /**
     * 更新学期
     *
     * @author zwy
     * @date 2018/11/30 14:47
     */
    @RequestMapping("/updateterm")
    @POST
    public Map updateTerm(Term term){
        return termService.updateTerm(term);
    }

    /**
     * 删除学期
     *
     * @author zwy
     * @date 2018/11/30 14:49
     */
    @RequestMapping("/deleteterm")
    @POST
    public Map delete(@RequestParam(value = "idList[]") List<Integer> idList){
        return termService.deleteTerm(idList);
    }

    /**
     * 根据 id 查询学期详情
     *
     * @author zwy
     * @date 2019/1/29 14:02
     */
    @RequestMapping("/detail")
    @GET
    public Map detail(Integer id){
        Term term = termService.queryTermById(id);
        return ResultUtils.success(term);
    }

    /**
     * 激活学期
     *
     * @author zwy
     * @date 2019/3/5 17:41
     */
    @RequestMapping("/active")
    @POST
    public Map activeTerm(Integer id){
        return termService.activeTerm(id);
    }

    /**
     * 禁用学期
     *
     * @author zwy
     * @date 2019/3/15 21:06
     */
    @RequestMapping("/disable")
    @POST
    public Map disableTerm(Integer id){
        return termService.disableTermById(id);
    }

    /**
     * 查询当前是否有激活学期
     *
     * @author zwy
     * @date 2019/3/15 21:07
     */
    @RequestMapping("/iftermactive")
    @GET
    public Map ifTermActive(){
        return termService.ifTermActive();
    }
}
