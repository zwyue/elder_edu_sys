package com.zrtjoa.service.impl;

import com.zrtjoa.annotation.PagingQuery;
import com.zrtjoa.dao.WorkNotesDao;
import com.zrtjoa.entity.WorkNotes;
import com.zrtjoa.service.WorkNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班主任工作手记管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class WorkNotesServiceImpl implements WorkNotesService {

    @Autowired
    private WorkNotesDao workNotesDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return workNotesDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WorkNotes record) {
        return workNotesDao.insert(record);
    }

    @Override
    public int insertSelective(WorkNotes record) {
        return workNotesDao.insertSelective(record);
    }

    @Override
    public WorkNotes selectByPrimaryKey(Integer id) {
        return workNotesDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkNotes record) {
        return workNotesDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WorkNotes record) {
        return workNotesDao.updateByPrimaryKey(record);
    }

    @Override
    @PagingQuery
    public List<WorkNotes> getList(Integer tid) {
        return workNotesDao.getList(tid);
    }

    @Override
    @PagingQuery
    public List<WorkNotes> getLists() {
        return workNotesDao.getLists();
    }

    @Override
    @PagingQuery
    public List<WorkNotes> getTitleList(String title) {
        return workNotesDao.getTitleList(title);
    }

    @Override
    @PagingQuery
    public List<WorkNotes> getTitleLists(Integer tid,String title) {
        return workNotesDao.getTitleList(title);
    }
}
