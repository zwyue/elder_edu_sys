package com.zhu.base.dao;

import com.zhu.base.entity.Courses;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Courses record);

    int insertSelective(Courses record);

    Courses selectByPrimaryKey(Integer id);

    int updateByPlanIdSelective(Courses record);

    int updateByPrimaryKey(Courses record);

    /**
     * 查询课程列表
     *
     * @author zwy
     * @date 2018/12/6 17:00
     * @param courses
     * @return list
     */
    List<Courses> queryCourseList(Courses courses);

    /**
     * 根据排课id查询课表
     *
     * @author zwy
     * @date 2019/3/4 15:42
     * @param id 排课id
     */
    Courses queryCourseByPlanId(Integer id);

    /**
     * 根据planId删除课程表
     *
     * @author zwy
     * @date 2019/3/5 14:32
     */
    Integer deleteByPlanIdSelective(Integer planId);
}