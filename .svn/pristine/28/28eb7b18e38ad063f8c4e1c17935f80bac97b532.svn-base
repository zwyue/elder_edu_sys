package com.zrtjoa.service;

import com.zrtjoa.entity.Category;

import java.util.List;

/**
 * 类别管理
 * @author yangli
 * @date 2018/12/25
 */
public interface CategoryService {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> getcatelist();

    /**
     * 获取含有班级的分类
     *
     * @author zwy
     * @date 2019/2/15 15:02
     * @return lsit
     */
    List<Category> cateHasCls();

    /**
     * 获取全部分类
     *
     * @author zwy
     * @date 2019/3/11 16:14
     */
    List<Category> allCategories();
}
