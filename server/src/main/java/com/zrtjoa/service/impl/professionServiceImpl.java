package com.zrtjoa.service.impl;

import com.zrtjoa.dao.ProfessionDao;
import com.zrtjoa.entity.Classes;
import com.zrtjoa.entity.Profession;
import com.zrtjoa.service.ClassesService;
import com.zrtjoa.service.ProfessionService;
import org.omg.CORBA.COMM_FAILURE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.zrtjoa.constant.SysConstant.Punctuation.COMMA;

/**
 * 专业管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class professionServiceImpl implements ProfessionService {

    @Autowired
    private ProfessionDao professionDao;

    @Autowired
    private ClassesService classesService ;

    @Autowired
    private RedisTemplate redisTemplate ;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return professionDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Profession record) {
        return professionDao.insert(record);
    }

    @Override
    public int insertSelective(Profession record) {
        return professionDao.insertSelective(record);
    }

    @Override
    public Profession selectByPrimaryKey(Integer id) {
        return professionDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Profession record) {
        return professionDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Profession record) {
        return professionDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Profession> getprolist(Integer cateid) {
        return professionDao.getprolist(cateid);
    }

    @Override
    public List<Profession> queryProfsByCateId(String cateIds) {
        List<Profession> professions = (List<Profession>) redisTemplate.opsForValue().get("professions");

        return professions.stream().filter(profession ->  {
            List<Classes> classes = new ArrayList<>();
            Arrays.asList(cateIds.split(COMMA)).forEach(cateId->{
                List<Classes> cls = new ArrayList<>();
                if (profession.getCateid().toString().equals(cateId)){
                    cls = classesService.queryClsByCateAndPrf(null,profession.getId());
                    classes.addAll(cls);
                }
            });
            return classes.size()>0;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Profession> queryAllProfsByCateId(String cateIds) {
        List<Profession> professions = (List<Profession>) redisTemplate.opsForValue().get("professions");
        return professions.stream().filter(profession ->cateIds.equals(profession.getCateid().toString())).collect(Collectors.toList());
    }
}
