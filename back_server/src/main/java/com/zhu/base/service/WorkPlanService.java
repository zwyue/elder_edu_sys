package com.zhu.base.service;

import com.zhu.base.entity.WorkPlan;

import java.util.List;
import java.util.Map;

/**
 * 班级工作计划管理
 * @author yangli
 * @date 2018/12/25
 */
public interface WorkPlanService {

    int deleteByPrimaryKey(Integer id);

    int insert(WorkPlan record);

    int insertSelective(WorkPlan record);

    WorkPlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkPlan record);

    int updateByPrimaryKey(WorkPlan record);

    List<WorkPlan> getList(Map map);

    List<WorkPlan> getLists();

    List<WorkPlan> getTitleList(Map map);

    List<WorkPlan> getTitleLists(String classname);
}
