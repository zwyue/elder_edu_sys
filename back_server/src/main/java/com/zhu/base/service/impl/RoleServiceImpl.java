package com.zhu.base.service.impl;

import com.zhu.base.constant.SysConstant;
import com.zhu.base.service.PowerService;
import com.zhu.base.service.RoleService;
import com.zhu.base.service.TeacherService;
import com.zhu.base.dao.initializingDao.RoleInitializingDao;
import com.zhu.base.entity.Role;
import com.zhu.base.entity.Teacher;
import com.zhu.base.vo.PowerVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import javax.ws.rs.POST;
import java.util.*;
import java.util.stream.Collectors;

/**
 * RoleServiceImpl class
 *
 * @author admin
 * @date 2018/11/23 11:31
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final static Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    private TeacherService teacherService ;

    @Autowired
    private RoleInitializingDao roleInitializingDao ;

    @Autowired
    private TransactionTemplate transactionTemplate ;

    @Autowired
    private PowerService powerService ;

    @Override
    public List<Role> queryRoleByRoleIds(List<String> roleList) {
        return roleInitializingDao.queryRoleByRoleIds(roleList);
    }

    /**
     * 查询权限
     *
     * @author zwy
     * @date 2018/12/6 14:13
     */
    @Override
    public Map queryRole(Role roleParam, Integer page, Integer size) {
        logger.info("..........查询角色列表.........");
        return roleInitializingDao.roleList(roleParam,page,size);
    }

    @Override
    public Integer createNewRole(Role role) {
        logger.info("新建角色");
        try {
            return roleInitializingDao.insertSelective(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 分配权限
     *
     * @author zwy
     * @date 2018/12/6 14:14
     */
    @Override
    public Integer allocateRole(Integer userId, Integer roleId) {
        logger.info("角色绑定权限");
        //根据用户id查询用户
        Teacher teacher = teacherService.queryTeacherById(userId);
        //根据角色id查询角色
        Assert.isTrue(StringUtils.isBlank(teacher.getRoleid()),"该用户已绑定角色");

        Role role = roleInitializingDao.selectByPrimaryKey(roleId);
        boolean hasLoginType = false ;

        if(StringUtils.isBlank(teacher.getRoleid())){
            hasLoginType = StringUtils.isNotBlank(role.getPowerid());
            teacher.setRoleid(roleId.toString());
        }else {
            //当前分配的角色是否已包含在用户已有角色中
            if(teacher.getRoleid().contains(roleId.toString())){
                return 0 ;
            }else {
                //已有角色是否包含权限
                List<Role> roles = roleInitializingDao.queryRoleByRoleIds(Arrays.asList(teacher.getRoleid().split(SysConstant.Punctuation.COMMA)));
                //但凡拥有的角色包含权限，则更新密码，有登陆权限
                hasLoginType = roles.stream().anyMatch(r-> StringUtils.isNotBlank(r.getPowerid())) ;

                teacher.setRoleid(teacher.getRoleid() + SysConstant.Punctuation.COMMA + roleId.toString());
            }
        }
        //用户是否无初始密码且当前分配的角色含有登陆权限
        if(StringUtils.isBlank(teacher.getPassword())&&hasLoginType){
            teacher.setPassword(SysConstant.INIT_PASSWORD);
        }
        return teacherService.updateTeacher(teacher);
    }

    @Override
    public Integer deleteRole(Integer roleId) {
        //查询所有绑定该角色的用户
        List<Teacher> teachers = teacherService.queryTeacherByRoleId(roleId);

        return transactionTemplate.execute(tran-> {
                //删除用户绑定的角色id(更新操作)
                teachers.forEach(teacher -> {
                    List<String> roleStringList = Arrays.asList(teacher.getRoleid().split(SysConstant.Punctuation.COMMA));
                    //重新组合用户所拥有的权限id
                    teacher.setRoleid(String.join(SysConstant.Punctuation.COMMA,roleStringList.stream().filter(rid->roleId!=Integer.parseInt(rid)).collect(Collectors.toList())));
                    teacherService.updateTeacher(teacher);
                });
                //删除角色
                return roleInitializingDao.deleteByPrimaryKey(roleId);
        });
    }

    /**
     * 用户解绑角色
     *
     * @author zwy
     * @date 2018/12/11 18:12
     */
    @Override
    public Integer untiedRole(Teacher teacher) {
        //todo-解绑角色
        return teacherService.updateTeacher(teacher);
    }

    @Override
    public Map<String,Object> queryRoleDetail(Integer roleId) {
        Role role = roleInitializingDao.selectByPrimaryKey(roleId);
        List<PowerVo> powers = new ArrayList<>();
        if(role!=null && StringUtils.isNotBlank(role.getPowerid())) {
            powers = powerService.queryPowerList(role.getPowerid());
        }
        Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
        map.put("role",role);
        map.put("powers",powers);
        return map;
    }

    @Override
    public Integer updateRole(Role role) {
        return roleInitializingDao.updateByPrimaryKeySelective(role);
    }

    @Override
    public Role selectByPrimaryKey(Integer id) {
        return roleInitializingDao.selectByPrimaryKey(id);
    }
}
