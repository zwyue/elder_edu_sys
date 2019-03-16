package com.zrtjoa.service.impl;

import com.zrtjoa.annotation.PagingQuery;
import com.zrtjoa.dao.StudentRecordDao;
import com.zrtjoa.entity.Roster;
import com.zrtjoa.entity.StudentRecord;
import com.zrtjoa.service.StudentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 学籍管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class StudentRecordServiceImpl implements StudentRecordService {

    @Autowired
    private StudentRecordDao recordDao;


    @Override
    @PagingQuery
    public List<StudentRecord> getList(Integer termid,Integer classid) {
        return recordDao.getList(termid,classid);
    }

    @Override
    public int insertList(List<Roster> list) {
        return recordDao.insertList(list);
    }

    @Override
    public Integer deleteMany(Integer id){
        return recordDao.deleteMany(id);
    }
}
