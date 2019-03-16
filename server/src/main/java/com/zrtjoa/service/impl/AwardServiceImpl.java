package com.zrtjoa.service.impl;

import com.zrtjoa.annotation.PagingQuery;
import com.zrtjoa.dao.AwardDao;
import com.zrtjoa.entity.Award;
import com.zrtjoa.service.AwardServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获奖信息管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class AwardServiceImpl implements AwardServie {

    @Autowired
    private AwardDao awardDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return awardDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Award award){
        return awardDao.insert(award);
    }

    @Override
    public int insertSelective(Award record) {
        return awardDao.insertSelective(record);
    }

    @Override
    public Award selectByPrimaryKey(Integer id) {
        return awardDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Award record) {
        return awardDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Award record) {
        return awardDao.updateByPrimaryKey(record);
    }

    @Override
    @PagingQuery
    public List<Award> getList() {
        return awardDao.getList();
    }

    @Override
    @PagingQuery
    public List<Award> getTitleList(String title) {
        return awardDao.getTitleList(title);
    }
}
