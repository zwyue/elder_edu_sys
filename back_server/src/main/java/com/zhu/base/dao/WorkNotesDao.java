package com.zhu.base.dao;

import com.zhu.base.entity.WorkNotes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkNotesDao {
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