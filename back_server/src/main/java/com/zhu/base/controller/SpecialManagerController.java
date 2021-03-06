package com.zhu.base.controller;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.common.TimeUtil;
import com.zhu.base.entity.SpecialManager;
import com.zhu.base.entity.Teacher;
import com.zhu.base.entity.Term;
import com.zhu.base.service.SpecialManagerService;
import com.zhu.base.service.TermService;
import com.zhu.base.util.DocUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhu.base.constant.SysConstant.Punctuation.COMMA;

/**
 * 特殊学员管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/specialmanager")
public class SpecialManagerController extends BaseController {

    @Autowired
    private SpecialManagerService specialManagerService;

    @Autowired
    private TermService termService;

    /**
     * 查询特殊学员列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        PageInfo<SpecialManager> pageInfo;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            pageInfo = new PageInfo<>(specialManagerService.getLists());
        }else{
            Map map = new HashMap();
            map.put("classids", Arrays.asList(teacher.getClassid().split(COMMA)));
            pageInfo = new PageInfo<>(specialManagerService.getList(map));
        }
        return ResultUtils.success(pageInfo);
    }

    /**
      * 根据名称和异动原因查询特殊学员记录
      * @author yangli
      * @date 2019/1/10
      * @param sname,types
      */
    @RequestMapping(value = "getStuByName",method = RequestMethod.GET)
    public Map getStuByName(String sname,String types,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        PageInfo<SpecialManager> pageInfo;
        Map map = new HashMap();
        map.put("sname", sname);
        map.put("types", types);
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            pageInfo = new PageInfo<>(specialManagerService.getNameType(map));
        }else{
            map.put("classids", Arrays.asList(teacher.getClassid().split(COMMA)));
            pageInfo = new PageInfo<>(specialManagerService.getNameTypes(map));
        }
        return ResultUtils.success(pageInfo);
    }

    /**
     * 特殊学员新增保存
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(SpecialManager record, HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        Term term = termService.queryThisTerm();
        if(term==null || "".equals(term)){
            return ResultUtils.success("保存失败");
        }else{
            record.setTid(teacher.getId());
            record.setTname(teacher.getTname());
            record.setTermid(term.getId());
            record.setTermname(term.getTerm());
            specialManagerService.insert(record);
            return ResultUtils.success("保存成功");
        }
    }

    /**
     * 查询特殊学员档案信息列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "querylist",method = RequestMethod.GET)
    public Map queryList(String idcard){
        List<SpecialManager> list = specialManagerService.queryList(idcard);
        return ResultUtils.success(list);
    }

    /**
     * 查询某一条的特殊学员信息
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer id){
        SpecialManager record = specialManagerService.selectByPrimaryKey(id);
        return ResultUtils.success(record);
    }

    /**
     * 修改特殊学员信息
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map update(SpecialManager record,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        Term term = termService.queryThisTerm();
        record.setTid(teacher.getId());
        record.setTname(teacher.getTname());
        record.setTermid(term.getId());
        record.setTermname(term.getTerm());
        specialManagerService.updateByPrimaryKey(record);
        return ResultUtils.success("修改成功");
    }

    /**
     * 删除一条特殊学员
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Map delete(Integer id){
        Integer delete = specialManagerService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.success("删除成功");
        }
    }

    /**
     * 导出特殊学员管理并下载word文档
     * @author yangli
     * @date 2019/1/23
     */
    @RequestMapping(value = "export")
    public void getDoc(HttpServletRequest request, HttpServletResponse response, Integer id) {
        String names="specialmanager.xml";
        SpecialManager specialManager=specialManagerService.selectByPrimaryKey(id);
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("sname", specialManager.getSname());
        dataMap.put("sex", specialManager.getSex());
        dataMap.put("filtime", TimeUtil.getUserDate(specialManager.getFiltime()));
        if( "0".equals(specialManager.getTypes())){
            dataMap.put("types", "退班");
        }else if ("1".equals(specialManager.getTypes())){
            dataMap.put("types", "转班");
        }else if ("2".equals(specialManager.getTypes())){
            dataMap.put("types", "休学");
        }else {
            dataMap.put("types", "开除");
        }
        dataMap.put("content", specialManager.getContent());
        dataMap.put("times", TimeUtil.getUserDate(specialManager.getTimes()));
        dataMap.put("address", specialManager.getAddress());
        dataMap.put("tname", specialManager.getTname());
        dataMap.put("question", specialManager.getQuestion());
        dataMap.put("options", specialManager.getOptions());
        String newWordName = "特殊学员档案记录.doc";
        //调用打印word的函数
        DocUtil.download(request, response, newWordName, dataMap,names);
    }
}
