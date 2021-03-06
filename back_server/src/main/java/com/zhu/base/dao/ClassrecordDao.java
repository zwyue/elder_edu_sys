package com.zhu.base.dao;

import com.zhu.base.entity.Classrecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassrecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Classrecord record);

    int insertSelective(Classrecord record);

    Classrecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classrecord record);

    int updateByPrimaryKey(Classrecord record);

    /**
     * 查询教室使用历史
     *
     * @author zwy
     * @date 2018/12/5 14:47
     * @param roomid 查询使用条件
     * @return list
     */
    List<Classrecord> clsRmUsageHistory(Integer roomid);
}