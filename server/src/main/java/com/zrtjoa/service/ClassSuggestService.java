package com.zrtjoa.service;

import com.zrtjoa.entity.ClassSuggest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 班级问题和建议清单管理
 * @author yangli
 * @date 2018/12/25
 */
public interface ClassSuggestService {
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
     * 导出班级问题和建议清单
     *
     * @author zwy
     * @date 2019/1/21 17:44
     * @param response 响应
     * @param ids 查询的id
     */
    void exportClassIssueAndRecommendation(HttpServletResponse response, Integer... ids);
}
