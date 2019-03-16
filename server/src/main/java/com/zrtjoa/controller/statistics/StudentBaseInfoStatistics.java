package com.zrtjoa.controller.statistics;

import com.zrtjoa.common.ResultUtils;
import com.zrtjoa.service.statistics.StuBaseStatisticsService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Map;

import static com.zrtjoa.util.ExcelUtil.getHSSFWorkbook;
import static com.zrtjoa.util.FileUtil.setResponseHeader;
import static com.zrtjoa.util.WordDocument.addImagesToWordDocument;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2019/1/15 9:33
 *     email        1092478224@qq.com
 *     desc         学生基本信息统计
 * </pre>
 */
@Controller
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
    @ResponseBody
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
    @ResponseBody
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
    @ResponseBody
    /*public Map stuSexRate(Integer startTermId , Integer endTermId , Integer classId){
        Map map = stuBaseStatisticsService.queryStuSexRate(startTermId,endTermId,classId);
        return ResultUtils.success(map) ;
    }*/
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
    @ResponseBody
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
    @ResponseBody
    public Map problemStatistics() {
        Calendar now = Calendar.getInstance();
        int endYear = now.get(Calendar.YEAR);
        int startYear = endYear-4;
        Map map = stuBaseStatisticsService.queryProblem(String.valueOf(startYear), String.valueOf(endYear));
        return ResultUtils.success(map);
    }
}
