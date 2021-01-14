package com.zhu.base.service.impl;

import com.zhu.base.annotation.PagingQuery;
import com.zhu.base.service.SpecialManagerService;
import com.zhu.base.dao.SpecialManagerDao;
import com.zhu.base.entity.SpecialManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 特殊学员管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class SpecialManagerServiceImpl implements SpecialManagerService {

    @Autowired
    private SpecialManagerDao specialManagerDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return specialManagerDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SpecialManager record) {
        return specialManagerDao.insert(record);
    }

    @Override
    public int insertSelective(SpecialManager record) {
        return specialManagerDao.insertSelective(record);
    }

    @Override
    public SpecialManager selectByPrimaryKey(Integer id) {
        return specialManagerDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SpecialManager record) {
        return specialManagerDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SpecialManager record) {
        return specialManagerDao.updateByPrimaryKey(record);
    }

    @Override
    public List<SpecialManager> queryList(String idcard) {
        return specialManagerDao.queryList(idcard);
    }

    @Override
    public List<SpecialManager> getLists() {
        return specialManagerDao.getLists();
    }

    @Override
    @PagingQuery
    public List<SpecialManager> getList(Map map) {
        return specialManagerDao.getList(map);
    }

    @Override
    public List<SpecialManager> getNameType(Map map) {
        return specialManagerDao.getNameType(map);
    }

    @Override
    @PagingQuery
    public List<SpecialManager> getNameTypes(Map map) {
        return specialManagerDao.getNameTypes(map);
    }
}
