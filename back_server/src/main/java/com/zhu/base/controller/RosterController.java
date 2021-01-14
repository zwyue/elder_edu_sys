package com.zhu.base.controller;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.entity.*;
import com.zhu.base.service.*;
import com.zrtjoa.entity.*;
import com.zrtjoa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 花名册管理
 * @author yangli
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/roster")
public class RosterController extends BaseController {

    @Autowired
    private RosterService rosterService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private TermService termService;

    @Autowired
    private StudentRecordService recordService;

    @Autowired
    private ClassesService classesService;

    /**
     * 查询花名册学生列表
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Map list(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        PageInfo<Roster> pageInfo;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            List<Classes> list = classesService.getList();
            pageInfo = new PageInfo<>(rosterService.getList(list.get(0).getId()));
        }else{
            String s = teacher.getClassid();
            String[] data = s.split(",");
            pageInfo=new PageInfo<Roster>(rosterService.getList(Integer.parseInt(data[0])));
        }
        return ResultUtils.success(pageInfo);
    }

    /**
      * 查询班级列表
      * @author yangli
      * @date 2019/3/1
      * @return list
      */
    @RequestMapping(value = "clist",method = RequestMethod.GET)
    public Map clist(HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        List<Classes> list;
        if("".equals(teacher.getClassid()) || teacher.getClassid()==null){
            list = classesService.getList();
        }else{
            list = classesService.getTeaClasslists(teacher.getId());
        }
        return ResultUtils.success(list);
    }

    /**
     * 根据班级查询学生列表
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map query(Integer classid){
        PageInfo<Roster> pageInfo = new PageInfo<>(rosterService.getList(classid));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 查询学生身份信息列表
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "identity",method = RequestMethod.GET)
    public Map identity(){
        List<Identitys> list = identityService.getList();
        return ResultUtils.success(list);
    }

    /**
     * 设置班委会
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map save(Integer classid,Roster roster){
        if ("学生".equals(roster.getIsleader())){
            rosterService.updateByPrimaryKey(roster);
            return ResultUtils.success("设置成功");
        }else{
            Roster rosters = rosterService.selectByIsleader(roster.getIsleader(),classid);
            if (rosters==null || "".equals(rosters)){
                rosterService.updateByPrimaryKey(roster);
                return ResultUtils.success("设置成功");
            }else{
                return ResultUtils.error("请勿重复设置");
            }
        }
    }

    /**
      * 导出到学籍管理
      * @author yangli
      * @date 2019/3/4
      * @param termid,classid
      * @return list
      */
    @RequestMapping(value = "conversion")
    @ResponseBody
    public Map Conversion(Integer termid,Integer classid){
        List<StudentRecord> slist = recordService.getList(termid, classid);
        List<Roster> rlist = rosterService.getList(classid);
        if (slist.size()>0){
            for (int i = 0; i < slist.size(); i++) {
                 recordService.deleteMany(slist.get(i).getId());
            }
        }
        recordService.insertList(rlist);
        return ResultUtils.success("转入到学籍成功");
    }

    /**
     * 导出签到表
     *
     * @author zwy
     * @date 2019/1/11 11:28
     */
    @RequestMapping("/exportcheckinform")
    public void exportCheckInForm(HttpServletResponse response, Integer classesId, Integer termId){
        classesService.exportCheckInForm(response,classesId,termId);
    }
}
