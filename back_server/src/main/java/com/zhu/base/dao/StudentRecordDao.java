package com.zhu.base.dao;

import com.zhu.base.entity.Roster;
import com.zhu.base.entity.StudentRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRecordDao {

    List<StudentRecord> getList(Integer termid, Integer classid);

    int insertList(List<Roster> list);

    Integer deleteMany(Integer id);
}