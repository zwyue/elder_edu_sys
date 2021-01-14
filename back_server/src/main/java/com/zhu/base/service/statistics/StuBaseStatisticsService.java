package com.zhu.base.service.statistics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2019/1/15 9:39
 *     email        1092478224@qq.com
 *     desc         学生基本信息统计
 * </pre>
 */
public interface StuBaseStatisticsService {

    /**
     * 查询年龄比率
     *
     * @author zwy
     * @date 2019/1/15 9:43
     * @param startTermId 开始学期
     * @param endTermId 结束学期
     * @param classId 班级id
     * @return map
     */
    Map queryStuAgeRate(Integer startTermId, Integer endTermId, Integer classId,Integer majorId);

    Map queryStuSexRates();

    /**
     * 奖励统计
     *
     * @author zwy
     * @date 2019/1/18 9:23
     * @param startYear 开始年
     * @param endYear 结束年
     * @return map
     */
    Map queryReward(String startYear, String endYear);

    /**
     * 问题统计
     *
     * @author zwy
     * @date 2019/1/18 9:57
     * @param startYear 开始年
     * @param endYear 结束年
     * @return map
     */
    Map queryProblem(String startYear, String endYear);

    /**
     * 导出word图表
     *
     * @author zwy
     * @date 2019/1/21 15:39
     * @param request 请求
     * @param response 回应
     */
    void exportWord(HttpServletRequest request, HttpServletResponse response);
}
