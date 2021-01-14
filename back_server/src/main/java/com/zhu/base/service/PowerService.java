package com.zhu.base.service;

import com.zhu.base.entity.Power;
import com.zhu.base.vo.PowerVo;

import java.util.List;
import java.util.Map;

/**
 * PowerService interface
 *
 * @author zwy
 * @date 2018/12/1 10:45
 */
public interface PowerService {

    /**
     * 根据权限id查询id
     *
     * @author zwy
     * @date 2018/12/1 10:50
     * @param powerIds 权限id集合
     * @return list
     */
    Map<String,List<Power>> queryPowerByPowerIds(List<String> powerIds);

    /**
     * 查询权限列表
     *
     * @author zwy
     * @date 2018/12/1 15:36
     * @param powerIds 权限id
     * @return list
     */
    List<PowerVo> queryPowerList(String powerIds);

}
