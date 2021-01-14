package com.zhu.base.dao;

import com.zhu.base.entity.SpecialManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SpecialManagerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SpecialManager record);

    int insertSelective(SpecialManager record);

    SpecialManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpecialManager record);

    int updateByPrimaryKey(SpecialManager record);

    List<SpecialManager> queryList(String idcard);

    List<SpecialManager> getLists();

    List<SpecialManager> getList(Map map);

    List<SpecialManager> getNameType(Map map);

    List<SpecialManager> getNameTypes(Map map);

    /**
     * 问题统计
     *
     * @author zwy
     * @date 2019/1/18 10:03
     * @param map 查询参数，都不一定用得着
     * @return list
     */
    List<Map<String, Object>> queryProblem(Map<String, Object> map);
}