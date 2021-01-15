package com.zhu.base.controller.log;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.entity.*;
import com.zhu.base.service.CategoryService;
import com.zhu.base.service.ClassesService;
import com.zhu.base.service.ProfessionService;
import com.zhu.base.service.WorkPlanService;
import com.zhu.base.util.DocUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.zhu.base.constant.SysConstant.Punctuation.COMMA;

/**
 * 日志管理-工作计划管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/workplan")
public class WorkPlanController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WorkPlanController.class);

    @Autowired
    private WorkPlanService workPlanService;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProfessionService professionService;

    /**
     * 日志管理-工作计划列表
     * @author yangli
     * @date 2018/11/28 14:10
     */
    @RequestMapping(value = "list" ,method = RequestMethod.GET)
    public Map getList(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        PageInfo<WorkPlan> pageInfo;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            pageInfo = new PageInfo<>(workPlanService.getLists());
        }else{
            Map map = new HashMap();
            map.put("classids", Arrays.asList(teacher.getClassid().split(COMMA)));
            pageInfo = new PageInfo<>(workPlanService.getList(map));
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 查询所有类别列表
     * @author yangli
     * @date 2019/02/20
     */
    @RequestMapping(value = "catelist",method = RequestMethod.GET)
    public Map catelist(){
        List<Category> list = categoryService.getcatelist();
        return ResultUtils.success(list);
    }

    /**
     * 根据类别id查询所有专业列表
     * @author yangli
     * @date 2019/02/20
     */
    @RequestMapping(value = "prolist",method = RequestMethod.GET)
    public Map prolist(Integer cateid){
        List<Profession> list = professionService.getprolist(cateid);
        return ResultUtils.success(list);
    }

    /**
     * 根据专业id查询班级
     *
     * @author yangli
     * @date 2019/2/18 11:21
     */
    @RequestMapping(value="classlist",method = RequestMethod.GET)
    public Map queryClsByPrfs(Integer proid){
        List<Classes> clses = classesService.getCList(proid);
        return ResultUtils.success(clses) ;
    }

    /**
      * 根据班级名称查询工作计划列表
      * @author yangli
      * @date 2019/1/4
      * @return list
      */
    @RequestMapping(value = "getclasslist",method = RequestMethod.GET)
    public Map getclassList(HttpSession httpSession,String classname){
        Teacher teacher = getLoginUser(httpSession);
        PageInfo<WorkPlan> pageInfo;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            pageInfo = new PageInfo<>(workPlanService.getTitleLists(classname));
        }else{
            Map map = new HashMap();
            map.put("classids", Arrays.asList(teacher.getClassid().split(COMMA)));
            map.put("classname",classname);
            pageInfo = new PageInfo<>(workPlanService.getTitleList(map));
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 判断是否为班主任
     * @author yangli
     * @date 2019/2/26
     * @return list
     */
    @RequestMapping(value = "judgeheader",method = RequestMethod.GET)
    public Map judgeheader(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        Object judge;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            judge="0";
        }else{
            judge="1";
        }
        return ResultUtils.success(judge);
    }

    /**
     * 班级列表
     * @author yangli
     * @date 2019/2/26
     * @return list
     */
    @RequestMapping(value = "getClass",method = RequestMethod.GET)
    public Map getClass(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        PageInfo<Classes> pageInfo;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            pageInfo = new PageInfo<>(classesService.getList());
        }else{
            pageInfo = new PageInfo<>(classesService.getTeaClasslists(teacher.getId()));
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 工作计划新增保存
     * @author yangli
     * @date 2018/11/28 14:10
     */
    @RequestMapping(value = "save" ,method = RequestMethod.POST)
    @ResponseBody
    public Map save(WorkPlan workPlan, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        workPlan.setTid(teacher.getId());
        workPlan.setTname(teacher.getTname());
        workPlanService.insert(workPlan);
        return ResultUtils.success("保存成功");
    }

    /**
     * 日志管理-工作计划修改页面
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "detail" ,method = RequestMethod.GET)
    public Map detail(Integer id){
        WorkPlan workPlan = workPlanService.selectByPrimaryKey(id);
        return ResultUtils.success(workPlan);
    }

    /**
     * 日志管理-工作计划修改
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    @ResponseBody
    public Map update(WorkPlan workPlan, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        workPlan.setTid(teacher.getId());
        workPlan.setTname(teacher.getTname());
        workPlanService.updateByPrimaryKey(workPlan);
        return ResultUtils.success("修改成功");
    }

    /**
     * 日志管理-工作计划删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        int delete = workPlanService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.error("删除成功");
        }
    }

    /**
     * 日志管理-班级工作计划导出
     * @author yangli
     * @date 2019/1/9
     */
    @RequestMapping(value = "export")
    public void getDoc(HttpServletRequest request, HttpServletResponse response, Integer id) {
        String names="workplan.xml";
        String times;
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("M");//可以方便地修改日期格式
        Integer hehe = Integer.parseInt(dateFormat.format(now));
        if(hehe>9 || hehe<2){
            times="下学期";
        }else{
            times="上学期";
        }
        WorkPlan workPlan=workPlanService.selectByPrimaryKey(id);
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("time", times);
        dataMap.put("content", workPlan.getContent());
        String newWordName = "班级工作计划记录.doc";
        //调用打印word的函数
        DocUtil.download(request, response, newWordName, dataMap,names);
    }
}
