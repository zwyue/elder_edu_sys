package com.zhu.base.dao;

import com.zhu.base.entity.Award;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AwardDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Award record);

    int insertSelective(Award record);

    Award selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Award record);

    int updateByPrimaryKey(Award record);

    List<Award> getList();

    List<Award> getTitleList(String title);

    /**
     * 获奖统计
     *
     * @author zwy
     * @date 2019/1/18 9:29
     * @param map 查询条件
     * @return list
     */
    List<Map<String, Object>> queryRewardTotal(Map<String, Object> map);
}