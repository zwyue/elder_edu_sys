package com.zhu.base.controller;

import com.github.pagehelper.PageInfo;
import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.entity.Courses;
import com.zhu.base.entity.WeekClass;
import com.zhu.base.service.CourseService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.PageData;
import javax.ws.rs.GET;
import java.util.*;

import static com.zhu.base.util.FileUtil.setResponseHeader;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/6 15:30
 *     email        1092478224@qq.com
 *     desc         课程管理
 * </pre>
 */
@RestController
@RequestMapping("course")
public class CourseController extends BaseController {

    @Autowired
    private CourseService courseService ;

    /**
     * 查看课程表
     *
     * @author zwy
     * @date 2018/12/6 15:36
     */
    @RequestMapping("/scanCurriculum")
    @GET
    public Map scanCurriculum(Courses courses){
        courses.setFlag("1");
        List<WeekClass> weekClasses = courseService.queryCourseList(courses);
        return ResultUtils.success(weekClasses);
    }

    /**
     * 导出课程表到excel
     *
     * @author zwy
     * @date 2019/1/7 9:14
     */
    @RequestMapping("export")
    @GET
    @ResponseBody
    public Map exportExcel(HttpServletResponse response,Courses courses){
        try {
            courseService.exportCourse(response,courses);
        }catch (Exception e){
            return ResultUtils.error(e);
        }
        return ResultUtils.success();
    }
}
