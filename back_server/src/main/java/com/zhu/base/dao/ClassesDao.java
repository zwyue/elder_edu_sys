package com.zhu.base.dao;

import com.zhu.base.entity.Classes;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClassesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    List<Classes> getList();

    List<Classes> byNameList(String name);

    List<Classes> getCList(Integer majorid);

    List<Classes> getTeaClasslist(Integer tid);

    List<Classes> getTeaClasslists(Integer headmaster);

    /**
     * 根据条件查询班级classes
     *
     * @author zwy
     * @date 2019/1/10 14:45
     * @param classes 班级查询条件
     * @return list
     */
    List<Classes> queryClasses(Classes classes);

    /**
     * 根基分类id和专业id
     *
     * @author zwy
     * @date 2019/1/25 15:10
     * @param map 查询条件
     * @return classes
     */
    List<Classes> queryClassesByCateIdAndPrfId(Map map);

    /**
     * 根据班级id查询班级
     *
     * @author zwy
     * @date 2019/2/14 15:20
     */
    List<Classes> queryClsByIds(List<String> asList);

    /**
     * 根据专业id查询班级
     *
     * @author zwy
     * @date 2019/2/18 11:31
     */
    List<Classes> getClsByPrfIds(List<String> asList);
}