package com.zhu.base.controller.statistics;

import com.zhu.base.common.ResultUtils;
import com.zhu.base.service.statistics.StuBaseStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.Calendar;
import java.util.Map;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2019/1/15 9:33
 *     email        1092478224@qq.com
 *     desc         学生基本信息统计
 * </pre>
 */
@RestController
@RequestMapping("/statistics/student/base")
public class StudentBaseInfoStatistics {

    @Autowired
    private StuBaseStatisticsService stuBaseStatisticsService ;

    /**
     * 年龄统计
     *
     * @author zwy
     * @date 2019/1/16 11:27
     */
    @RequestMapping("/agerate")
    @GET
    public Map stuAgeRate(Integer startTermId , Integer endTermId , Integer classId,Integer majorId){
        Map map = stuBaseStatisticsService.queryStuAgeRate(startTermId,endTermId,classId,majorId);
        return ResultUtils.success(map) ;
	}

    /**
     * 导出word图表（现解决方法是用图片导出，后续根据需求再做调整）
     *
     * @author zwy
     * @date 2019/1/21 15:27
     */
    @RequestMapping(value = "/wordoutput")
    @POST
    public void exportWord(HttpServletRequest request, HttpServletResponse response){
        stuBaseStatisticsService.exportWord(request,response);
    }

    /**
     * 性别统计
     *
     * @author zwy
     * @date 2019/1/16 11:28
     */
    @RequestMapping("/sexrate")
    @GET
    public Map stuSexRate(){
        Map map = stuBaseStatisticsService.queryStuSexRates();
        return ResultUtils.success(map) ;
    }

    /**
     * 奖励统计
     *
     * @author zwy
     * @date 2019/1/18 9:21
     */
    @RequestMapping("/rewardstatistics")
    @GET
    public Map rewardStatistics(){
        Calendar now = Calendar.getInstance();
        int endYear = now.get(Calendar.YEAR);
        int startYear = endYear-4;
        Map map = stuBaseStatisticsService.queryReward(String.valueOf(startYear), String.valueOf(endYear));
        return ResultUtils.success(map) ;
    }

    /**
     * 问题统计
     *
     * @author zwy
     * @date 2019/1/18 11:01
     */
    @RequestMapping("/problemstatistics")
    @GET
    public Map problemStatistics() {
        Calendar now = Calendar.getInstance();
        int endYear = now.get(Calendar.YEAR);
        int startYear = endYear-4;
        Map map = stuBaseStatisticsService.queryProblem(String.valueOf(startYear), String.valueOf(endYear));
        return ResultUtils.success(map);
    }
}
