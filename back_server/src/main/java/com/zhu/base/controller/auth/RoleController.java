package com.zhu.base.controller.auth;

import com.zhu.base.common.ResultUtils;
import com.zhu.base.entity.Role;
import com.zhu.base.entity.Teacher;
import com.zhu.base.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.Map;

/**
 * RoleController class
 *
 * @author zwy
 * @date 2018/11/23 11:30
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService ;

    /**
     * 查看角色列表
     *
     * @author zwy
     * @date 2019/1/3 9:26
     */
    @RequestMapping("/role-list")
    @GET
    public Map<String, Object> queryRoleList(Role role,Integer page,Integer size){
        logger.info("........查看角色列表.......");
        return ResultUtils.success(roleService.queryRole(role,page,size));
    }

    /**
     * 创建角色
     *
     * @author zwy
     * @date 2018/12/11 17:02
     */
    @RequestMapping(value = "/create")
    @POST
    public Map<String, Object> createNewRole(Role role){
        roleService.createNewRole(role);
        return ResultUtils.success() ;
    }

    /**
     * 删除角色
     *
     * @author zwy
     * @date 2018/12/11 17:04
     */
    @RequestMapping(value = "/delete")
    @DELETE
    public Map<String, Object> deleteRoleByRoleId(Integer roleId){
        logger.info(".......删除角色.......id:{}",roleId);
        Role role = roleService.selectByPrimaryKey(roleId);
        if("班主任".equals(role.getRolename()) || "管理员".equals(role.getRolename())){
            return ResultUtils.error("无法删除班主任或管理员角色");
        }else{
            roleService.deleteRole(roleId);
            return ResultUtils.success() ;
        }
    }

    /**
     * 用户分配角色
     *
     * @author zwy
     * @date 2018/12/11 17:02
     */
    @RequestMapping(value = "/allocate")
    @POST
    public Map<String, Object> allocateRole(Integer userId,Integer roleId){
        logger.info("......{}用户绑定角色{}......",userId,roleId);
        roleService.allocateRole(userId,roleId);
        return ResultUtils.success();
    }

    /**
     * 用户角色解绑
     *
     * @author zwy
     * @date 2018/12/11 18:07
     */
    @RequestMapping(value = "/untiedrole")
    @POST
    public Map<String, Object> untiedRole(Teacher teacher){
        logger.info("......用户绑定角色{}......",teacher.getId());
        return ResultUtils.success(roleService.untiedRole(teacher));
    }

    /**
     * 查看角色详情
     *
     * @author zwy
     * @date 2019/2/20 11:39
     */
    @RequestMapping("/detail")
    @GET
    public Map<String, Object> roleDetail(Integer roleId){
        return ResultUtils.success(roleService.queryRoleDetail(roleId)) ;
    }

    /**
     * 更新角色信息
     *
     * @author zwy
     * @date 2019/2/20 14:18
     */
    @RequestMapping("/update")
    @GET
    public Map<String, Object> updateRole(Role role){
        return ResultUtils.success(roleService.updateRole(role)) ;
    }
}
