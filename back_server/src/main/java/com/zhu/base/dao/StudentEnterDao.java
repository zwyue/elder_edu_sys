package com.zhu.base.dao;

import com.zhu.base.entity.StudentEnter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author sys
 * @date 2018/12/27 9:01
 */
@Repository
public interface StudentEnterDao {

    /**
     * 根据主键删除报名信息
     *
     * @author zwy
     * @date 2018/12/27 8:56
     * @param id 主键
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入报名信息
     *
     * @author zwy
     * @date 2018/12/27 8:57
     * @param record 记录
     * @return int
     */
    int insert(StudentEnter record);

    /**
     * 根据条件插入
     *
     * @author zwy
     * @date 2018/12/27 8:58
     * @param record 记录
     * @return int
     */
    int insertSelective(StudentEnter record);

    /**
     * 根据主键查询报名信息
     *
     * @author zwy
     * @date 2018/12/27 8:58
     * @param id 主键
     * @return studentEnter
     */
    StudentEnter selectByPrimaryKey(Integer id);

    /**
     * 有条件更新
     *
     * @author zwy
     * @date 2018/12/27 8:59
     * @param record 记录
     * @return int
     */
    int updateByPrimaryKeySelective(StudentEnter record);

    /**
     * 更新报名信息
     *
     * @author zwy
     * @date 2018/12/27 9:00
     * @param record 记录
     * @return int
     */
    int updateByPrimaryKey(StudentEnter record);

    /**
     * 根据学生id删除学生信息
     *
     * @author zwy
     * @date 2018/12/27 8:55
     * @param stuId 学生id
     * @return int
     */
    Integer deleteByStuId(Integer stuId);

    /**
     * 根据报名信息id查询报名的classid
     *
     * @author zwy
     * @date 2018/12/27 11:21
     * @param id 报名信息id
     * @return id
     */
    StudentEnter queryEnterByEnterId(Integer id);

    /**
     * 根据学生id查询报名信息
     *
     * @author zwy
     * @date 2019/3/3 17:50
     */
    StudentEnter queryEnterByStuId(Integer id);

    /**
     * 根据学生id更新报名信息
     *
     * @author zwy
     * @date 2019/3/3 17:53
     */
    Integer updateByStuId(StudentEnter studentEnter);

    /**
     * 根据学生id和学期id查询学生是否报过名
     *
     * @author zwy
     * @date 2019/3/5 9:29
     */
    StudentEnter selectByStuIdAndTermId(Map<String, Object> map);

    /**
     * 查询当前学期学生的报名信息
     *
     * @author zwy
     * @date 2019/3/5 16:51
     */
    StudentEnter queryEnterByStuIdThisTerm(Map<String, Object> map);

    /**
     * 根据学期id查询学生报名信息
     *
     * @author zwy
     * @date 2019/3/5 18:20
     */
    List<StudentEnter> queryEnterByTermId(Integer id);

    /**
     * 根据学生id和学期id删除学生报名信息
     *
     * @author zwy
     * @date 2019/3/13 15:32
     */
    int deleteByStuIdAndTermId(Map<String, Object> map);
}