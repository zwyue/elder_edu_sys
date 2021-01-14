package com.zhu.base.dao;

import com.zhu.base.entity.MeetRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MeetRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MeetRecord record);

    int insertSelective(MeetRecord record);

    MeetRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetRecord record);

    int updateByPrimaryKey(MeetRecord record);

    List<MeetRecord> getList(Map map);

    List<MeetRecord> getLists();

    List<MeetRecord> getTitleList(String content);

    List<MeetRecord> getTitleLists(Map map);

    /**
     * 根据id查询班会记录
     *
     * @author zwy
     * @date 2019/1/23 9:19
     * @param ids 班会记录id
     * @return list
     */
    List<MeetRecord> queryRecordByIds(Integer[] ids);
}