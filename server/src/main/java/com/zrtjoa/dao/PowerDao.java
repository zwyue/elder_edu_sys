package com.zrtjoa.dao;

import com.zrtjoa.entity.Power;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface PowerDao {

    Power selectByPrimaryKey(Integer id);

    /**
     * 根据权限id查询权限
     *
     * @author zwy
     * @date 2018/12/1 10:54
     * @param powerIds 权限信息
     * @return list
     */
    List<Power> queryPowerByPowerIds(List<String> powerIds);

    /**
     * 查询权限列表
     *
     * @author zwy
     * @date 2018/12/1 15:37
     * @param powerList 权限id集合封装成map
     * @return list
     */
    List<Power> queryPowerList(List<String> powerList);

}