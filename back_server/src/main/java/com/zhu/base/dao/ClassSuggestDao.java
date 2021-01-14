package com.zhu.base.dao;

import com.zhu.base.entity.ClassSuggest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClassSuggestDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassSuggest record);

    int insertSelective(ClassSuggest record);

    ClassSuggest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassSuggest record);

    int updateByPrimaryKey(ClassSuggest record);

    List<ClassSuggest> getList(Map map);

    List<ClassSuggest> getLists();

    List<ClassSuggest> getTitleList(String title);

    List<ClassSuggest> getTitleLists(Map map);

    /**
     * 根据建议id查询问题与建议清单（最多5条）
     *
     * @author zwy
     * @date 2019/1/21 17:53
     * @param ids 问题与建议id
     * @return list
     */
    List<ClassSuggest> queryClsSug(Integer[] ids);
}