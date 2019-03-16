package com.zrtjoa.dao;

import com.zrtjoa.entity.Roster;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RosterDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Roster record);

    int insertSelective(Roster record);

    Roster selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Roster record);

    int updateByPrimaryKey(Roster record);

    List<Roster> getList(Integer classid);

    Roster selectByIsleader(String isleader,Integer classid);

    /**
     * 查询学生是否已存在
     *
     * @author zwy
     * @date 2018/12/24 13:50
     * @param map 包含学生id和班级id
     * @return roster
     */
    List<Roster> queryRosterInfoByClsIdAndStuId(Map map);

    /**
     * 根据学生id 删除
     *
     * @author zwy
     * @date 2018/12/27 9:03
     * @param stuId 学生id
     * @return int
     */
    Integer delByStuId(Integer stuId);

    /**
     * 根据班级id和学生id删除花名册学生信息
     *
     * @author zwy
     * @date 2018/12/27 13:49
     * @param stuNos 学生学号
     * @return int
     */
    int deleteByClassIdAndStuId(List<String> stuNos);

    /**
     * 根据学号删除花名册学生信息
     *
     * @author zwy
     * @date 2018/12/28 15:49
     * @param number 学号
     * @return int
     */
    Integer delByStuNo(String number);

    /**
     * 根据班级id导出班级花名册
     *
     * @author zwy
     * @date 2019/1/11 11:39
     * @param map 包括班级id和学期id
     * @return roster 花名册名单
     */
    List<Roster> queryRosterByClassIdAndTerm(Map map);

    /**
     * 根据学期查询每学期学生总数
     *
     * @author zwy
     * @date 2019/1/14 15:00
     * @param map 查询条件
     * @return map
     */
    List<Map<String,Object>> queryTotalStudentWithYear(Map<String, Object> map);

    /**
     * 新生率
     *
     * @author zwy
     * @date 2019/1/14 18:25
     * @param map 查询条件
     * @return list
     */
    List<Map<String, Object>> queryNewStuRate(Map<String, Object> map);

    /**
     * 查询年龄比率
     *
     * @author zwy
     * @date 2019/1/15 9:49
     * @param map 查询条件
     * @return list
     */
    List<Map<String, Object>> queryStuAgeRate(Map<String, Object> map);

    /**
     *  性别比率
     *
     * @author zwy
     * @date 2019/1/16 11:33
     * @param map 查询条件
     * @return list
     */
    List<Map<String, Object>> queryStuSexRate(Map<String, Object> map);

    List<Map<String, Object>> queryStuSexRates();

    /**
     * 流失率
     *
     * @author zwy
     * @date 2019/1/16 16:23
     * @param map 查询条件
     * @return list
     */
    List<Map<String, Object>> queryStuLossRate(Map<String, Object> map);

    /**
     * 根据学生id和班级id查询学生学号
     *
     * @author zwy
     * @date 2019/3/2 15:38
     */
    List<String> queryStuNumByStuIdAndClsIds(Map map);

    /**
     * 查询学生学号列表
     *
     * @author zwy
     * @date 2019/3/5 13:47
     * @return string
     */
    List<String> queryStuNo();

    /**
     * 根据学生id查询截取班级学号
     *
     * @author zwy
     * @date 2019/3/12 15:16
     */
    List<String> querySubStuNumByStuId(Integer id);

    /**
     * 根据学生id和截取的学生学号查询学生
     *
     * @author zwy
     * @date 2019/3/13 11:30
     */
    Map<String,String> queryRosterBySubStuNumAndStuId(Map queryMap);

    /**
     * 根据学期和学生id删除学生
     *
     * @author zwy
     * @date 2019/3/13 15:30
     */
    int delByStuIdAndTermId(Map<String, Object> map);

    /**
     * 根据学生id和学期id查询花名册
     *
     * @author zwy
     * @date 2019/3/13 15:47
     */
    List<Roster> queryRosterByStuIdAndTermId(Map<String, Object> map);
}