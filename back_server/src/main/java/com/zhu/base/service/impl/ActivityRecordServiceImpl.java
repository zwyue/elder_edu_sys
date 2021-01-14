package com.zhu.base.service.impl;

import com.zhu.base.annotation.PagingQuery;
import com.zhu.base.service.ActivityRecordService;
import com.zhu.base.dao.ActivityRecordDao;
import com.zhu.base.entity.ActivityRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志管理-学校活动记录管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class ActivityRecordServiceImpl implements ActivityRecordService {

    @Autowired
    private ActivityRecordDao recordDao;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return recordDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActivityRecord record) {
        return recordDao.insert(record);
    }

    @Override
    public int insertSelective(ActivityRecord record) {
        return recordDao.insertSelective(record);
    }

    @Override
    public ActivityRecord selectByPrimaryKey(Integer id) {
        return recordDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActivityRecord record) {
        return recordDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActivityRecord record) {
        return recordDao.updateByPrimaryKey(record);
    }

    @Override
    @PagingQuery
    public List<ActivityRecord> getList() {
        return recordDao.getList();
    }

    @Override
    @PagingQuery
    public List<ActivityRecord> getTitleList(String title) {
        return recordDao.getTitleList(title);
    }
}
