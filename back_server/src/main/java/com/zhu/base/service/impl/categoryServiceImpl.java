package com.zhu.base.service.impl;

import com.zhu.base.service.CategoryService;
import com.zhu.base.service.ClassesService;
import com.zhu.base.dao.CategoryDao;
import com.zhu.base.entity.Category;
import com.zhu.base.entity.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 类别管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class categoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ClassesService classesService ;

    @Autowired
    private RedisTemplate redisTemplate ;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return categoryDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Category record) {
        return categoryDao.insert(record);
    }

    @Override
    public int insertSelective(Category record) {
        return categoryDao.insertSelective(record);
    }

    @Override
    public Category selectByPrimaryKey(Integer id) {
        return categoryDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Category record) {
        return categoryDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Category record) {
        return categoryDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Category> getcatelist() {
        return categoryDao.getcatelist();
    }

    @Override
    public List<Category> cateHasCls() {
        List<Category> cateHasCls = (List<Category>) redisTemplate.opsForValue().get("categories");

        return cateHasCls.stream().filter(category -> {
            List<Classes> classes = classesService.queryClsByCateAndPrf(category.getId(),null);
            //只保留有班级的分类
            return classes.size()>0;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Category> allCategories() {
        return  (List<Category>) redisTemplate.opsForValue().get("categories");
    }
}
