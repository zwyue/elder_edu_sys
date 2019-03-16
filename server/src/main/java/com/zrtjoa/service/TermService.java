package com.zrtjoa.service;

import com.zrtjoa.entity.Teacher;
import com.zrtjoa.entity.Term;

import java.util.List;
import java.util.Map;

/**
 * TermService interface
 * 学期管理
 *
 * @author zwy
 * @date 2018/11/29 14:47
 */
public interface TermService {

    /**
     * 创建新学期，复制学期
     *
     * @author zwy
     * @date 2018/11/29 15:57
     * @param term
     * @return int
     */
    Integer createNewTerm(Term term);

    /**
     * 使学期不可用
     * todo -（可与使学期可用合并为一个更新方法）
     *
     * @author zwy
     * @date 2018/11/29 17:53
     * @return int
     */
    Integer disableTerm();

    /**
     * 使学期可用
     *
     * @author zwy
     * @date 2018/11/29 18:11
     * @return int
     */
    Integer enableTerm();

    /**
     * 查询学期列表
     *
     * @author zwy
     * @date 2018/11/30 9:26
     * @return list
     */
    List<Term> queryTermList();

    /**
     * 更新学期状态
     *
     * @author zwy
     * @date 2018/11/30 10:20
     * @return int
     */
    Map updateTermStatus();

    /**
     * 修改学期信息
     *
     * @author zwy
     * @date 2018/11/30 13:58
     * @return int
     */
    Map updateTerm(Term term);

    /**
     * 删除学期
     *
     * @author zwy
     * @date 2018/11/30 14:50
     * @param idList
     * @return int
     */
    Map deleteTerm(List<Integer> idList);

    /**
     * 复制学期（时间增加一年）
     *
     * @author zwy
     * @date 2018/11/30 17:34
     * @param id
     * @return int
     */
    Integer copyTerm(Integer id, Teacher teacher);

    /**
     * 查询本学期
     *
     * @author zwy
     * @date 2019/1/5 13:00
     * @return term
     */
    Term queryThisTerm();

    /**
     * 根据id查询学期
     *
     * @author zwy
     * @date 2019/1/29 14:00
     * @param id 查询id
     * @return term
     */
    Term queryTermById(Integer id);

    /**
     * 激活学期
     *
     * @author zwy
     * @date 2019/3/5 18:03
     * @param id 学期id
     * @return int
     */
    Map activeTerm(Integer id);

    /**
     * 禁用学期
     *
     * @author zwy
     * @date 2019/3/5 18:34
     */
    Map disableTermById(Integer id);

    /**
     * 查询当前是否有激活学期
     *
     * @author zwy
     * @date 2019/3/5 19:13
     */
    Map ifTermActive();
}
