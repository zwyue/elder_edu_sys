package com.zrtjoa.controller.student;

import com.github.pagehelper.PageInfo;
import com.zrtjoa.common.BaseController;
import com.zrtjoa.common.ResultUtils;
import com.zrtjoa.entity.Identitys;
import com.zrtjoa.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author: yangli
 * @Description:
 * @Date: Created in 14:27 2018/12/20
 * @Modified by:
 */
@RestController
@RequestMapping("/identity")
public class IdentityController extends BaseController {

    @Autowired
    private IdentityService identityService;

   /**
     * 查询学生身份维护列表
     * @author yangli
     * @date 2019/1/11
     * @return list
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(){
        PageInfo<Identitys> pageInfo=new PageInfo<Identitys>(identityService.getList());
        return ResultUtils.success(pageInfo);
    }

    /**
      * 学生身份维护新增页面
      * @author yangli
      * @date 2019/1/11
      */
    /*@RequestMapping(value = "add",method = RequestMethod.GET)
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/identity/identity_add");
        return modelAndView;
        //return "student/identity/identity_add";
    }*/

    /**
      * 学生身份新增保存
      * @author yangli
      * @date 2019/1/11
      * @param record
      * @return record
      */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(Identitys record){
        identityService.insert(record);
        return ResultUtils.success("保存成功");
    }

    /**
      * 学生身份修改页面
      * @author yangli
      * @date 2019/1/11
      * @param id,model
      * @return record
      */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id){
        Identitys identitys = identityService.selectByPrimaryKey(id);
        return ResultUtils.success(identitys);
    }

    /**
      * 学生身份修改保存
      * @author yangli
      * @date 2019/1/11
      * @param identitys
      * @return identitys
      */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map update(Identitys identitys){
        identityService.updateByPrimaryKey(identitys);
        return ResultUtils.success("修改成功");
    }

    /**
      * 学生身份维护信息删除
      * @author yangli
      * @date 2019/1/11
      * @param id
      */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = identityService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else{
            return ResultUtils.success("删除成功");
        }
    }

}
