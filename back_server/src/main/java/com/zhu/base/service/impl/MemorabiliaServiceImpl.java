package com.zhu.base.service.impl;

import com.zhu.base.annotation.PagingQuery;
import com.zhu.base.service.MemorabiliaService;
import com.zhu.base.dao.MemorabiliaDao;
import com.zhu.base.entity.Memorabilia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 大事记
 *
 * @author zwy
 * @date 2018/11/28 14:04
 */
@Service
public class MemorabiliaServiceImpl implements MemorabiliaService {

    private static final Logger logger = LoggerFactory.getLogger(MemorabiliaServiceImpl.class);

    @Autowired
    private MemorabiliaDao memorabiliaDao ;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return memorabiliaDao.deleteByPrimaryKey(id);
    }

    /**
     * 保存大事记
     *
     * @author zwy
     * @date 2018/11/28 15:29
     */
    @Override
    public Integer saveMemorabilia(Memorabilia memorabilia) {
        return memorabiliaDao.saveMemorabilia(memorabilia);
    }

    /**
     * 大事记更新
     *
     * @author zwy
     * @date 2018/11/28 15:29
     */
    @Override
    public Integer updateMemorabilia(Memorabilia memorabilia) {
        return memorabiliaDao.updateMemorabilia(memorabilia);
    }

    /**
     * 根据id查询大事记
     *
     * @author zwy
     * @date 2018/11/28 15:29
     */
    @Override
    public Memorabilia queryMemorabiliaById(Integer id) {
        return memorabiliaDao.queryMemorabiliaById(id);
    }

    /**
     * 查询大事记列表
     *
     * @author zwy
     * @date 2018/11/28 15:30
     */
    @Override
    @PagingQuery
    public List<Memorabilia> queryAllMemorabilia(Memorabilia memorabilia) {
        return memorabiliaDao.queryMemorabilia(memorabilia);
    }

    @Override
    @PagingQuery
    public List<Memorabilia> getTitleList(String title) {
        return memorabiliaDao.getTitleList(title);
    }
}
