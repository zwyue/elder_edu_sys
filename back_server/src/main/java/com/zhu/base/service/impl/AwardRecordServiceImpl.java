package com.zhu.base.service.impl;

import com.zhu.base.annotation.PagingQuery;
import com.zhu.base.service.AwardRecordService;
import com.zhu.base.dao.AwardRecordDao;
import com.zhu.base.entity.AwardRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获奖情况记录管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class AwardRecordServiceImpl implements AwardRecordService {

    @Autowired
    private AwardRecordDao awardRecordDao;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return awardRecordDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AwardRecord record) {
        return awardRecordDao.insert(record);
    }

    @Override
    public int insertSelective(AwardRecord record) {
        return awardRecordDao.insertSelective(record);
    }

    @Override
    public AwardRecord selectByPrimaryKey(Integer id) {
        return awardRecordDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AwardRecord record) {
        return awardRecordDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AwardRecord record) {
        return awardRecordDao.updateByPrimaryKey(record);
    }

    @Override
    @PagingQuery
    public List<AwardRecord> getLists() {
        return awardRecordDao.getLists();
    }

    @Override
    @PagingQuery
    public List<AwardRecord> getList(Integer tid) {
        return awardRecordDao.getList(tid);
    }

    @Override
    @PagingQuery
    public List<AwardRecord> getTitleList(String title) {
        return awardRecordDao.getTitleList(title);
    }

    @Override
    @PagingQuery
    public List<AwardRecord> getTitleLists(Integer tid, String title) {
        return awardRecordDao.getTitleLists(tid,title);
    }
}
