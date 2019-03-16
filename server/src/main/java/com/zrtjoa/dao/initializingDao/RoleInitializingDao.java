package com.zrtjoa.dao.initializingDao;

import com.sun.corba.se.impl.orbutil.ObjectUtility;
import com.zrtjoa.dao.RoleDao;
import com.zrtjoa.entity.Role;
import com.zrtjoa.enums.StatusEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.zrtjoa.constant.SysConstant.MAP_DEFAULT_SIZE;
import static com.zrtjoa.util.ObectUtils.checkObjFieldIsNotNull;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/13 11:19
 *     email        1092478224@qq.com
 *     desc         初始化缓存角色
 * </pre>
 */
@Repository
public class RoleInitializingDao implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(RoleInitializingDao.class);

    //缓存数据库全部角色（角色较多时不建议缓存）
    private static List<Role> roles = new ArrayList<>();

    @Autowired
    private RoleDao roleDao ;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("..........初始化角色........");
        roles = roleDao.roleList() ;
    }

    /**
     * 根据roleId查询角色
     *
     * @author zwy
     * @date 2018/12/13 16:37
     */
    public List<Role> queryRoleByRoleIds(List<String> roleIds) {

        List<Integer> intRoleIds = new ArrayList<>();
        //将String转化为Integer
        roleIds.forEach(ri->intRoleIds.add(Integer.parseInt(ri)));
        //将角色包括roleIds的角色存入list并返回
        return roles.stream().filter(r->intRoleIds.contains(r.getId())).collect(Collectors.toList());
    }

    /**
     * 查询角色列表(查询条件包括角色名、权限名、状态)
     *
     * @author zwy
     * @date 2018/12/13 16:37
     */
    public Map<String,Object> roleList(Role role, Integer page, Integer size) {

        Integer total = roles.size();
        List<Role> roleList =checkObjFieldIsNotNull(role)?roles.stream().filter(r->
                r.getRolename().equals(role.getRolename())
                        ||(StringUtils.isNotBlank(r.getPowername())
                        && StringUtils.isNotBlank(role.getPowername())
                        && r.getPowername().contains(role.getPowername()))
                        ||r.getStatus().equals(role.getStatus())
        ).collect(Collectors.toList()):roles;

        if(page!=null&size!=null){
            int pageSize = page*size>total?total:page*size ;
            roleList = roleList.subList((page-1)*size,pageSize);
        }

        roleList.forEach(r -> r.setStatusTxt(StatusEnum.returnEnumByCode(r.getStatus()).msg));

        Map<String,Object> map = new HashMap<>(MAP_DEFAULT_SIZE);
        map.put("total",total);
        map.put("list",roleList);
        return map ;
    }

    /**
     * 新建角色
     *
     * @author zwy
     * @date 2018/12/13 16:38
     */
    public Integer insertSelective(Role role) throws Exception {
        int returnInsert = roleDao.insertSelective(role);
        afterPropertiesSet();
        return returnInsert ;
    }

    /**
     * 根据角色id查询角色
     *
     * @author zwy
     * @date 2018/12/13 16:40
     */
    public Role selectByPrimaryKey(Integer roleId) {
        List<Role> roleList = roles.stream().filter(role->roleId.equals(role.getId())).collect(Collectors.toList());
        return roleList.size()==0?null:roleList.get(0);
    }

    /**
     * 删除角色
     *
     * @author zwy
     * @date 2018/12/13 16:49
     */
    public Integer deleteByPrimaryKey(Integer roleId) {
        logger.info("...........删除角色.........");
        int delete = roleDao.deleteByPrimaryKey(roleId);
        if(delete>0){
            try {
                afterPropertiesSet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return delete ;
    }

    public Integer updateByPrimaryKeySelective(Role role){
        logger.info("......更新角色信息.......");
        int update = roleDao.updateByPrimaryKeySelective(role);
        if(update>0){
            try {
                afterPropertiesSet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return update ;
    }
}
