package com.zhu.base.service;

import com.zhu.base.entity.Role;
import com.zhu.base.entity.Teacher;

import java.util.List;
import java.util.Map;

/**
 * RoleService interface
 *
 * @author admin
 * @date 2018/11/23 11:30
 */
public interface RoleService {

    /**
     * 根据
     *
     * @author zwy
     * @date 2018/12/1 10:19
     * @param roleList 角色id list
     * @return list
     */
    List<Role> queryRoleByRoleIds(List<String> roleList);

    /**
     * 查询角色列表
     *
     * @author zwy
     * @date 2018/12/1 16:20
     * @param role 角色查询条件
     * @param page 页号
     * @param size 大小
     * @return list
     */
    Map queryRole(Role role, Integer page, Integer size);

    /**
     * 创建新角色
     *
     * @author zwy
     * @date 2018/12/3 8:57
     * @param role 角色信息
     * @return int
     */
    Integer createNewRole(Role role);

    /**
     * 为人员分配角色
     *
     * @author zwy
     * @date 2018/12/3 9:53
     * @param userId 人员id
     * @param roleId 角色id
     * @return int
     */
    Integer allocateRole(Integer userId, Integer roleId);

    /**
     * 删除角色
     *
     * @author zwy
     * @date 2018/12/11 17:06
     * @param roleId 角色id
     * @return int
     */
    Integer deleteRole(Integer roleId);

    /**
     * 用户角色解绑
     *
     * @author zwy
     * @date 2018/12/11 18:11
     * @param teacher
     * @return int
     */
    Integer untiedRole(Teacher teacher);

    /**
     * 根据角色id查询角色
     *
     * @author zwy
     * @date 2019/2/20 11:42
     * @param roleId 角色id
     * @return role
     */
    Map<String,Object> queryRoleDetail(Integer roleId);

    /**
     * 更新角色
     *
     * @author zwy
     * @date 2019/2/20 14:28
     * @param role 角色信息
     * @return int
     */
    Integer updateRole(Role role);

    Role selectByPrimaryKey(Integer id);
}
