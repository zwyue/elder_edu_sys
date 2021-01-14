package com.zhu.base.service.impl;

import com.zhu.base.annotation.PagingQuery;
import com.zhu.base.service.WorkSummaryService;
import com.zhu.base.dao.WorkSummaryDao;
import com.zhu.base.entity.WorkSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班主任工作总结管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class WorkSummaryServiceImpl implements WorkSummaryService {

    @Autowired
    private WorkSummaryDao workSummaryDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return workSummaryDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WorkSummary record) {
        return workSummaryDao.insert(record);
    }

    @Override
    public int insertSelective(WorkSummary record) {
        return workSummaryDao.insertSelective(record);
    }

    @Override
    public WorkSummary selectByPrimaryKey(Integer id) {
        return workSummaryDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkSummary record) {
        return workSummaryDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WorkSummary record) {
        return workSummaryDao.updateByPrimaryKey(record);
    }

    @Override
    public List<WorkSummary> getLists() {
        return workSummaryDao.getLists();
    }

    @Override
    @PagingQuery
    public List<WorkSummary> getList(Integer tid) {
        return workSummaryDao.getList(tid);
    }

    @Override
    @PagingQuery
    public List<WorkSummary> getTitleList(String title) {
        return workSummaryDao.getTitleList(title);
    }

    @Override
    @PagingQuery
    public List<WorkSummary> getTitleLists(Integer tid, String title) {
        return workSummaryDao.getTitleLists(tid,title);
    }
}
