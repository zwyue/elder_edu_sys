package com.zhu.base.service;

import com.zhu.base.entity.MeetRecord;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 班会记录管理
 * @author yangli
 * @date 2018/12/26
 */
public interface MeetRecordService {
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
     * 导出班会excel
     *
     * @author zwy
     * @date 2019/1/23 9:16
     * @param response 响应
     * @param ids 选择的班会记录id
     */
    void exportMeetRecord(HttpServletResponse response, Integer... ids);
}
