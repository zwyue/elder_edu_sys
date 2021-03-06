package com.zhu.base.service.impl;

import com.zhu.base.annotation.PagingQuery;
import com.zhu.base.service.RosterService;
import com.zhu.base.dao.RosterDao;
import com.zhu.base.entity.Roster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 花名册管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class RosterServiceImpl implements RosterService {

    @Autowired
    private RosterDao rosterDao;

    /**
     * 删除学委会
     * @author yangli
     * @date 2018/12/25
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return rosterDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Roster record) {
        return rosterDao.insert(record);
    }

    @Override
    public int insertSelective(Roster record) {
        return rosterDao.insertSelective(record);
    }

    @Override
    public Roster selectByPrimaryKey(String id) {
        return rosterDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Roster record) {
        return rosterDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Roster record) {
        return rosterDao.updateByPrimaryKey(record);
    }

    @Override
    @PagingQuery
    public List<Roster> getList(Integer classid) {
         return rosterDao.getList(classid);
    }

    @Override
    public Roster selectByIsleader(String isleader,Integer classid) {
        return rosterDao.selectByIsleader(isleader,classid);
    }
}
