package com.zhu.base.dao;

import com.zhu.base.entity.StudyLog;

import java.util.List;

/**
 * @Author: yangli
 * @Description:
 * @Date: Created in 14:36 2018/12/19
 * @Modified by:
 */
public interface StudyLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(StudyLog record);

    int insertSelective(StudyLog record);

    StudyLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudyLog record);

    int updateByPrimaryKey(StudyLog record);

    List<StudyLog> getList();

    List<StudyLog> getTitleList(String title);
}
