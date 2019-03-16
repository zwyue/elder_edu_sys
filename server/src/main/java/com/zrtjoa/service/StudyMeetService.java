package com.zrtjoa.service;

import com.zrtjoa.entity.StudyMeet;

import java.util.List;

/**
 * 学委会管理
 * @author yangli
 * @date 2018/12/25
 */
public interface StudyMeetService {

    int deleteByPrimaryKey(Integer id);

    int insert(StudyMeet record);

    int insertSelective(StudyMeet record);

    StudyMeet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudyMeet record);

    int updateByPrimaryKey(StudyMeet record);

    List<StudyMeet> getList();

    int updateDuties(StudyMeet record);

    List<StudyMeet> getSnameList(String sname);
}
