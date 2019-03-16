package com.zrtjoa.service;

import com.zrtjoa.entity.StudyLog;

import java.util.List;

/**
 * 学委会日志管理
 * @author yangli
 * @date 2018/12/25
 */
public interface StudyLogService {
    int deleteByPrimaryKey(Integer id);

    int insert(StudyLog record);

    int insertSelective(StudyLog record);

    StudyLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudyLog record);

    int updateByPrimaryKey(StudyLog record);

    List<StudyLog> getList();

    List<StudyLog> getTitleList(String title);
}
