package com.zhu.base.service;

import com.zhu.base.entity.Classes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 班级管理
 * @author yangli
 * @date 2018/12/25
 */
public interface ClassesService {
    int deleteByPrimaryKey(Integer id);

    int insert(Classes record);

    int insertSelective(Classes record);

    /**
     * 查询班级信息
     *
     * @author zwy
     * @date 2019/2/26 13:20
     * @param id 班级id
     * @return map
     */
    Map queryClassInfo(Integer id);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    List<Classes> getList();

    List<Classes> byNameList(String name);

    List<Classes> getCList(Integer majorid);

    List<Classes> getTeaClasslist(Integer tid);

    List<Classes> getTeaClasslists(Integer headmaster);

    /**
     * 科任教师、班干部名单excel导出
     *
     * @author zwy
     * @date 2019/1/10 14:23
     * @param classes 班级查询条件
     * @param response 响应
     */
    void exportTeacherAndCadre(HttpServletResponse response,Classes classes);

    /**
     *  导出班级签到表
     *
     * @author zwy
     * @date 2019/1/11 11:30
     * @param httpServletResponse 响应
     * @param classesId 班级
     * @param termId 学期id
     */
    void exportCheckInForm(HttpServletResponse httpServletResponse,Integer classesId,Integer termId);

    /**
     * 根据分类id和专业id查询班级
     *
     * @author zwy
     * @date 2019/1/25 15:00
     * @param cateId 分类id
     * @param  prfId 专业id
     * @return list
     */
    List<Classes> queryClsByCateAndPrf(Integer cateId, Integer prfId);

    /**
     * 将班级及专业组装到类别中
     *
     * @author zwy
     * @date 2019/2/11 16:26
     */
    List<Map<String,Object>> queryClsCateToAcquireCls();

    /**
     * 根据专业id查询班级
     *
     * @author zwy
     * @date 2019/2/18 11:23
     * @param prfIds 专业id
     * @return list
     */
    List<Classes> queryClsByPrfs(String prfIds);

    /**
     * 根据班级名称查询班级
     *
     * @author zwy
     * @date 2019/2/22 17:02
     * @param className 班级名称
     * @return list
     */
    List<Classes> queryClass(String className);

    /**
     * 新建班级
     *
     * @author zwy
     * @date 2019/2/25 13:58
     * @param classes 班级信息
     * @return int
     */
    Integer addNewCls(Classes classes);

    /**
     * 根据班级id查询班级信息
     *
     * @author zwy
     * @date 2019/2/27 15:05
     * @param classIds 班级id
     * @return list
     */
    List<Classes> queryClassInfoByIds(List<String> classIds);
}
