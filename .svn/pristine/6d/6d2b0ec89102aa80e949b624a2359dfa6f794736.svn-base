package com.zrtjoa.service.statistics;

import java.util.Map;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author zwy
 *     @date 2019/1/14 14:24
 *     email        1092478224@qq.com
 *     desc
 * </pre>
 */
public interface StuNumStatisticsService {

    /**
     * 根据年份查询学生总数
     *
     * @author zwy
     * @date 2019/1/14 14:28
     * @param startTermId 学期开始ID
     * @param endTermId 学期结束ID
     * @param classId 班级ID
     * @return map
     */
    Map queryTotalStudentWithYear(Integer startTermId ,Integer endTermId,Integer majorId ,Integer classId);

    /**
     *  新生率
     *
     * @author zwy
     * @date 2019/1/14 18:23
     * @param startTermId 开始学期
     * @param endTermId 结束学期
     * @param classId 班级id
     * @return map
     */
    Map queryNewStuRate(Integer startTermId, Integer endTermId, Integer classId,Integer majorId);

    /**
     * 流失率
     *
     * @author zwy
     * @date 2019/1/16 16:22
     * @param startTermId 开始学期
     * @param endTermId 结束学期
     * @param classId 班级id
     * @return map
     */
    Map queryStuLossRate(Integer startTermId, Integer endTermId, Integer classId,Integer majorId);
}
