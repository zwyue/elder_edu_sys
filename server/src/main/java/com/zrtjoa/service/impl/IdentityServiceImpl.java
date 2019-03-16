package com.zrtjoa.service.impl;

import com.zrtjoa.annotation.PagingQuery;
import com.zrtjoa.dao.IdentitysDao;
import com.zrtjoa.entity.Identitys;
import com.zrtjoa.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生身份管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class IdentityServiceImpl implements IdentityService {

    @Autowired
    private IdentitysDao identitysDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return identitysDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Identitys record) {
        return identitysDao.insert(record);
    }

    @Override
    public int insertSelective(Identitys record) {
        return identitysDao.insertSelective(record);
    }

    @Override
    public Identitys selectByPrimaryKey(Integer id) {
        return identitysDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Identitys record) {
        return identitysDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Identitys record) {
        return identitysDao.updateByPrimaryKey(record);
    }

    @Override
    @PagingQuery
    public List<Identitys> getList() {
        return identitysDao.getList();
    }
}
