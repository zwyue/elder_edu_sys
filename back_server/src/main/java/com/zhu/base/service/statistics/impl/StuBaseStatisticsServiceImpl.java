package com.zhu.base.service.statistics.impl;

import com.zhu.base.constant.SysConstant;
import com.zhu.base.service.statistics.StuBaseStatisticsService;
import com.zhu.base.dao.AwardDao;
import com.zhu.base.dao.RosterDao;
import com.zhu.base.dao.SpecialManagerDao;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

import static com.zhu.base.util.FileUtil.setResponseHeader;
import static com.zhu.base.util.WordDocument.addImagesToWordDocument;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2019/1/15 9:40
 *     email        1092478224@qq.com
 *     desc         学生基础信息统计
 * </pre>
 */
@Service
public class StuBaseStatisticsServiceImpl implements StuBaseStatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(StuBaseStatisticsServiceImpl.class);

    @Autowired
    private RosterDao rosterDao ;

    @Autowired
    private AwardDao awardDao ;

    @Autowired
    private SpecialManagerDao specialManagerDao ;

    @Override
    public Map queryStuAgeRate(Integer startTermId, Integer endTermId, Integer classId,Integer majorId) {
        Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE) ;
        map.put("startTermId",startTermId);
        map.put("endTermId",endTermId);
        map.put("classId",classId);
        map.put("majorId",majorId);
        List<Map<String,Object>> list = rosterDao.queryStuAgeRate(map);
        map.put("statisticInfo",list);
        return map;
    }

    @Override
    public Map queryStuSexRates() {
        Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE) ;
        List<Map<String,Object>> list = rosterDao.queryStuSexRates();
        //解决list all elements are null ,注意此处list不为空
        list.removeAll(Collections.singleton(null));
        map.put("statisticInfo",list);
        return map;
    }

    @Override
    public Map queryReward(String startYear, String endYear) {
        Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE) ;
        map.put("startYear",startYear);
        map.put("endYear",endYear);
        //map.put("classId",classId);
        List<Map<String,Object>> list = awardDao.queryRewardTotal(map);
        map.put("statisticInfo",list);
        return map;
    }

    @Override
    public Map queryProblem(String startYear, String endYear) {
        Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE) ;
        map.put("startYear",startYear);
        map.put("endYear",endYear);
        //map.put("classId",classId);
        List<Map<String,Object>> list = specialManagerDao.queryProblem(map);
        map.put("statisticInfo",list);
        return map;
    }

    @Override
    public void exportWord(HttpServletRequest request, HttpServletResponse response){
        try {
            String imgUrl = request.getParameter("base64Info");
            if(!StringUtils.isEmpty(imgUrl)) {
                //拆分base64编码后部分
                String[] imgUrlArr = imgUrl.split("base64,");
                byte[] buffer = Base64.getDecoder().decode(imgUrlArr[1]);

                //记住系统如果为Linux，此处路径需改
                String picPath = "C:\\pic\\pic" + ".png";
                //图片文件
                File file = new File(picPath);
                if (!file .exists() && !file .isDirectory()){
                    logger.info(file + "不存在，...need..create");
                    if(file .mkdir()){
                        logger.info(file + " ..create..success ");
                    }
                } else {
                    logger.info("//目录存在");
                }

                //生成图片
                OutputStream out = new FileOutputStream(file);
                //图片输出流
                out.write(buffer);
                //清空流
                out.flush();
                //关闭流
                out.close();
//                XWPFDocument doc = addImagesToWordDocument(file);

                if (file.exists()) {
                    file.delete();//删除图片
                }
//                setResponseHeader(response, "test.doc",doc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
