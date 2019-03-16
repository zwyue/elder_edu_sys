package com.zrtjoa.service;

import com.zrtjoa.entity.Roster;
import com.zrtjoa.entity.StudentRecord;

import java.util.List;

/**
 * 花名册管理
 * @author yangli
 * @date 2018/12/25
 */
public interface StudentRecordService {
    List<StudentRecord> getList(Integer termid,Integer classid);

    int insertList(List<Roster> list);

    Integer deleteMany(Integer id);

}
