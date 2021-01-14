package com.zhu.base.service.statistics.impl;

import com.zhu.base.constant.SysConstant;
import com.zhu.base.service.statistics.StuNumStatisticsService;
import com.zhu.base.dao.RosterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author zwy
 *     @date 2019/1/14 14:25
 *     email        1092478224@qq.com
 *     desc
 * </pre>
 */
@Service
public class StuNumStatisticsServiceImpl implements StuNumStatisticsService {

    @Autowired
    private RosterDao rosterDao ;

    /**
     * 根据学期id和班级id查询学期学生总数
     *
     * @author zwy
     * @date 2019/1/14 15:38
     */
    @Override
    public Map queryTotalStudentWithYear(Integer startTermId ,Integer endTermId,Integer majorId ,Integer classId) {
        Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE) ;
        map.put("startTermId",startTermId);
        map.put("endTermId",endTermId);
        map.put("classId",classId);
        map.put("majorId",majorId);
        List<Map<String,Object>> list = rosterDao.queryTotalStudentWithYear(map);
        map.put("statisticInfo",list);
        return map ;
    }

    @Override
    public Map queryNewStuRate(Integer startTermId, Integer endTermId, Integer classId,Integer majorId) {
        Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE) ;
        map.put("startTermId",startTermId);
        map.put("endTermId",endTermId);
        map.put("classId",classId);
        map.put("majorId",majorId);
        List<Map<String,Object>> list = rosterDao.queryNewStuRate(map);
        map.put("statisticInfo",list);
        return map ;
    }

    @Override
    public Map queryStuLossRate(Integer startTermId, Integer endTermId, Integer classId,Integer majorId) {
        Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE) ;
        map.put("startTermId",startTermId);
        map.put("endTermId",endTermId);
        map.put("classId",classId);
        map.put("majorId",majorId);
        List<Map<String,Object>> list = rosterDao.queryStuLossRate(map);
        map.put("statisticInfo",list);
        return map ;
    }
}
