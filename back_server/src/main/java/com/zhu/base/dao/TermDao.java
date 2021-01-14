package com.zhu.base.dao;

import com.zhu.base.entity.Term;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TermDao class
 *
 * @author zwy
 * @date 2018/11/29 14:48
 */
@Repository
public interface TermDao {

    /**
     * 插入新学期
     *
     * @author zwy
     * @date 2019/3/15 21:08
     * @param record 学期实体类
     * @return int
     */
    int insert(Term record);

    /**
     * 根据id查询学期
     *
     * @author zwy
     * @date 2019/3/15 21:09
     * @param id 主键
     * @return term
     */
    Term selectByPrimaryKey(Integer id);

    /**
     * 更新学期
     *
     * @author zwy
     * @date 2019/3/15 21:10
     * @param record 学期实体类
     * @return int
     */
    int updateByPrimaryKey(Term record);

    /**
     * 查询上学期
     *
     * @author zwy
     * @date 2018/11/29 16:49
     * @param term 学期信息
     * @return term
     */
    Term selectLastTerm(Term term);

    /**
     * 使学期不可用
     *
     * @author zwy
     * @date 2018/11/29 17:58
     * @return int
     */
    Integer disableTerm();

    /**
     * 使学期可用
     *
     * @author zwy
     * @date 2018/11/29 18:12
     * @return int
     */
    Integer enableTerm();

    /**
     * 查询学期列表
     *
     * @author zwy
     * @date 2018/11/30 9:28
     * @return list
     */
    List<Term> queryTermList();

    /**
     * 查询本学期
     *
     * @author zwy
     * @date 2019/1/5 13:00
     * @return term
     */
    Term queryActiveTerm();

    /**
     * 激活学期
     *
     * @author zwy
     * @date 2019/3/5 18:04
     * @param id 需要激活的学期id
     * @return int
     */
    Integer enableTermById(Integer id);

    /**
     * 查询档期那学期
     *
     * @author zwy
     * @date 2019/3/5 18:29
     * @return term
     */
    Term queryThisTerm();

    /**
     * 根据id禁用学期
     *
     * @author zwy
     * @date 2019/3/5 18:36
     * @param id 学期id
     * @return int
     */
    Integer disableTermById(Integer id);
}