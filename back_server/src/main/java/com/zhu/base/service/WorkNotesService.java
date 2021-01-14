package com.zhu.base.service;

import com.zhu.base.entity.WorkNotes;

import java.util.List;

/**
 * 班主任工作手记管理
 * @author yangli
 * @date 2018/12/25
 */
public interface WorkNotesService {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkNotes record);

    int insertSelective(WorkNotes record);

    WorkNotes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkNotes record);

    int updateByPrimaryKey(WorkNotes record);

    List<WorkNotes> getList(Integer tid);

    List<WorkNotes> getLists();

    List<WorkNotes> getTitleList(String title);

    List<WorkNotes> getTitleLists(Integer tid,String title);
}
