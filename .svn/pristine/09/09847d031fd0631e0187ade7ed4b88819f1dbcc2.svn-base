package com.zrtjoa.service;

import com.zrtjoa.entity.Profession;

import java.util.List;

/**
 * 专业管理
 * @author yangli
 * @date 2018/12/25
 */
public interface ProfessionService {
    int deleteByPrimaryKey(Integer id);

    int insert(Profession record);

    int insertSelective(Profession record);

    Profession selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Profession record);

    int updateByPrimaryKey(Profession record);

    List<Profession> getprolist(Integer cateid);

    /**
     * 根据分类id查询专业列表
     *
     * @author zwy
     * @date 2019/2/13 13:25
     * @param cateIds 分类id
     * @return list
     */
    List<Profession> queryProfsByCateId(String cateIds);

    /**
     * 查询全部专业
     *
     * @author zwy
     * @date 2019/3/11 16:23
     */
    List<Profession> queryAllProfsByCateId(String cateIds);
}
