package com.zhu.base.controller.statistics;

import com.zhu.base.common.ResultUtils;
import com.zhu.base.service.statistics.StuNumStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import java.util.Map;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2019/1/14 14:17
 *     email        1092478224@qq.com
 *     desc         学生人数统计
 * </pre>
 */
@RestController
@RequestMapping("/statistics/student")
public class StudentNumberStatistics {

    @Autowired
    private StuNumStatisticsService stuNumStatisticsService ;

    /**
     * 学院总人数统计
     *
     * @author zwy
     * @date 2019/1/14 14:20
     */
    @RequestMapping("/total")
    @GET
    public Map totalByYear(Integer startTermId ,Integer endTermId ,Integer majorId,Integer classId){
        Map map = stuNumStatisticsService.queryTotalStudentWithYear(startTermId,endTermId,majorId ,classId);
        return ResultUtils.success(map);
    }

    /**
     * 新增率
     *
     * @author zwy
     * @date 2019/1/14 18:19
     */
    @RequestMapping("/newrate")
    @GET
    public Map newStuRatio(Integer startTermId ,Integer endTermId ,Integer classId,Integer majorId){
        Map map = stuNumStatisticsService.queryNewStuRate(startTermId,endTermId,classId,majorId);
        return ResultUtils.success(map) ;
    }

    /**
     * 人员流失率
     *
     * @author zwy
     * @date 2019/1/16 16:19
     */
    @RequestMapping("/lossrate")
    @GET
    public Map stuLossRatio(Integer startTermId ,Integer endTermId ,Integer classId,Integer majorId){
        Map map = stuNumStatisticsService.queryStuLossRate(startTermId,endTermId,classId,majorId);
        return ResultUtils.success(map) ;
    }
}
