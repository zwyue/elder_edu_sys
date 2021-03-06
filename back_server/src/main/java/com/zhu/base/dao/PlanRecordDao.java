package com.zhu.base.dao;

import com.zhu.base.entity.PlanRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PlanRecord record);

    int insertSelective(PlanRecord record);

    PlanRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlanRecord record);

    int updateByPrimaryKey(PlanRecord record);

    /**
     * 根据当前排课安排查询是否存在冲突
     *
     * @author zwy
     * @date 2018/12/6 9:28
     * @param planRecord 排课记录
     * @return int
     */
    Integer queryIfExist(PlanRecord planRecord);

    /**
     * 查询排课记录
     *
     * @author zwy
     * @date 2018/12/8 12:46
     * @return list
     */
    List<PlanRecord> queryAllPlanRecord(Integer classId);

    /**
     * 根据记录条件查询记录
     *
     * @author zwy
     * @date 2018/12/8 15:44
     * @param planRecord
     * @return list
     */
    List<String> queryPlanRecordByRecord(PlanRecord planRecord);

    /**
     * 根据id查询班级
     *
     * @author zwy
     * @date 2019/3/5 14:24
     */
    PlanRecord queryRecordById(Integer id);
}