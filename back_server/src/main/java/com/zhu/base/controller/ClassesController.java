package com.zhu.base.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.entity.Category;
import com.zhu.base.entity.Classes;
import com.zhu.base.entity.Profession;
import com.zhu.base.service.CategoryService;
import com.zhu.base.service.ClassesService;
import com.zhu.base.service.ProfessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.time.LocalTime;
import java.util.*;

import static com.zhu.base.constant.SysConstant.MAP_DEFAULT_SIZE;
import static com.zhu.base.exception.ExceptionEnum.SUCCESS;

/**
 * 班级管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/classes")
public class ClassesController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ClassesController.class);

    @Autowired
    private ClassesService classesService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProfessionService professionService;

    /**
     * 班级管理列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Map getList(){
        PageInfo<Classes> p=new PageInfo<Classes>(classesService.getList());
        return ResultUtils.success(p);
    }

    /**
     * 查询类别列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "/catelist",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getcateList(){
        //获取从service传来的list数据
        List<Category> list = categoryService.getcatelist();
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        Map<String,Object> returnMap = new HashMap<>(MAP_DEFAULT_SIZE);
        returnMap.put("catelist",jsonArray);
        return ResultUtils.success(returnMap,SUCCESS.errorMessage);
    }

    /**
     * 从缓存中取出教室类别列表（其中去除了没有班级的分类）
     *
     * @author zwy
     * @date 2019/1/25 14:12
     */
    @RequestMapping("/categories")
    @GET
    public Map classCateList(){
        return ResultUtils.success(categoryService.cateHasCls());
    }

    /**
     * 从缓存中取出全部分类（包含没有班级的分类）
     *
     * @author zwy
     * @date 2019/3/16 15:24
     */
    @RequestMapping("/all-categories")
    @GET
    public Map cateList(){
        return ResultUtils.success(categoryService.allCategories());
    }

    /**
     * 查询专业列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "prolist",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getprolist(Integer cateid){
        //获取从service传来的list数据
        //cateid=1;
        List<Profession> list = professionService.getprolist(cateid);
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        Map<String,Object> returnMap = new HashMap<>(MAP_DEFAULT_SIZE);
        returnMap.put("prolist",jsonArray);
        return ResultUtils.success(returnMap,SUCCESS.errorMessage);
    }

    /**
     * 获取缓存专业（其中去除了没有班级的专业）
     *
     * @author zwy
     * @date 2019/1/25 14:50
     */
    @RequestMapping("/professions")
    @GET
    public Map professions(String cateIds){
        List<Profession> professions = professionService.queryProfsByCateId(cateIds);
        return ResultUtils.success(professions);
    }

    /**
     * 获取缓存专业（包含没有班级的专业）
     *
     * @author zwy
     * @date 2019/3/16 15:25
     */
    @RequestMapping("/all-professions")
    @GET
    public Map allProfessions(String cateIds){
        List<Profession> professions = professionService.queryAllProfsByCateId(cateIds);
        return ResultUtils.success(professions);
    }

    /**
     * 根据专业id和类别id查询班级
     *
     * @author zwy
     * @date 2019/1/25 14:56
     */
    @RequestMapping("/queryclsbycateidandprfid")
    @GET
    public Map queryClsByCateAndPrf(Integer cateId , Integer prfId){
        List<Classes> clses = classesService.queryClsByCateAndPrf(cateId,prfId);
        return ResultUtils.success(clses) ;
    }

    /**
     * 根据专业id查询班级
     *
     * @author zwy
     * @date 2019/2/18 11:21
     */
    @RequestMapping("/queryClsByPrfs")
    @GET
    public Map queryClsByPrfs(String prfIds){
        List<Classes> clses = classesService.queryClsByPrfs(prfIds);
        return ResultUtils.success(clses) ;
    }

    /**
     * 班级列表
     *
     * @author zwy
     * @date 2019/2/22 17:00
     */
    @RequestMapping(value = "/class-list")
    @GET
    public Map classList(String className){
        PageInfo<Classes> p=new PageInfo<Classes>(classesService.queryClass(className));
        Map<String,Object> map = new HashMap<>(MAP_DEFAULT_SIZE);
        map.put("total",p.getTotal());
        map.put("pages",p.getPages());
        map.put("pageNum",p.getPageNum());
        map.put("list",p.getList());
        return ResultUtils.success(map) ;
    }


    /**
     * 查询班级列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "clist")
    @ResponseBody
    public Map<String,Object> getclist(Integer majorid){
        //majorid=1;
        List<Classes> list = classesService.getCList(majorid);
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        Map<String,Object> returnMap = new HashMap<>(MAP_DEFAULT_SIZE);
        returnMap.put("clist",jsonArray);
        return ResultUtils.success(returnMap,SUCCESS.errorMessage);
    }

    /**
     * 新建班级
     *
     * @author zwy
     * @date 2019/2/25 13:55
     */
    @RequestMapping("/newclass")
    @POST
    public Map addNewClass(Classes classes){
        return ResultUtils.success(classesService.addNewCls(classes));
    }

    /**
     * 班级管理修改页面
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id) {
        return ResultUtils.success(classesService.queryClassInfo(id));
    }

    /**
     * 班级管理修改
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map update(Classes classes){
        classesService.updateByPrimaryKey(classes);
        if(classesService.updateByPrimaryKey(classes)>0){
            return ResultUtils.success("修改成功");
        }else {
            return ResultUtils.success("修改失败");
        }
    }

    /**
     * 班级管理删除
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method =RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = classesService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.success("删除成功");
        }
    }

    /**
     * 科任教师、班干部名单excel导出
     *
     * @author zwy
     * @date 2019/1/10 14:20
     */
    @RequestMapping("/export-teacher-cadre")
    public void exportTeacherAndCadre(HttpServletResponse response, Classes classes){
        classesService.exportTeacherAndCadre(response,classes);
    }

    /**
     * 导出签到表
     *
     * @author zwy
     * @date 2019/1/11 11:28
     */
    @RequestMapping("/exportcheckinform")
    public void exportCheckInForm(HttpServletResponse response, Integer classesId,Integer termId){
        classesService.exportCheckInForm(response,classesId,termId);
    }

    /**
     * 查询类别以获取专业至最后获取具体班级
     *
     * @author zwy
     * @date 2019/2/11 16:23
     */
    @RequestMapping("/queryclscatetoacquirecls")
    @GET
    public Map queryClsCateToAcquireCls(){
        logger.info("开始时间：{}", LocalTime.now());
        List<Map<String,Object>> categories = classesService.queryClsCateToAcquireCls() ;
        logger.info("结束时间：{}", LocalTime.now());
        return ResultUtils.success(categories);
    }
}
