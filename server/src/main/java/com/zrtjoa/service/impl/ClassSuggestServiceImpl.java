package com.zrtjoa.service.impl;

import com.zrtjoa.dao.ClassSuggestDao;
import com.zrtjoa.entity.ClassSuggest;
import com.zrtjoa.entity.Classes;
import com.zrtjoa.service.ClassSuggestService;
import com.zrtjoa.service.ClassesService;
import com.zrtjoa.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.zrtjoa.constant.SysConstant.SheetTitle.*;
import static com.zrtjoa.util.FileUtil.setResponseHeader;
import static org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER;

/**
 * 班级问题和建议清单管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class ClassSuggestServiceImpl implements ClassSuggestService {

    @Autowired
    private ClassSuggestDao classSuggestDao;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return classSuggestDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ClassSuggest record) {
        return classSuggestDao.insert(record);
    }

    @Override
    public int insertSelective(ClassSuggest record) {
        return classSuggestDao.insertSelective(record);
    }

    @Override
    public ClassSuggest selectByPrimaryKey(Integer id) {
        return classSuggestDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ClassSuggest record) {
        return classSuggestDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ClassSuggest record) {
        return classSuggestDao.updateByPrimaryKey(record);
    }

    @Override
    public List<ClassSuggest> getList(Map map) {
        return classSuggestDao.getList(map);
    }

    @Override
    public List<ClassSuggest> getLists() {
        return classSuggestDao.getLists();
    }

    @Override
    public List<ClassSuggest> getTitleList(String title) {
        return classSuggestDao.getTitleList(title);
    }

    @Override
    public List<ClassSuggest> getTitleLists(Map map) {
        return classSuggestDao.getTitleLists(map);
    }

    @Override
    public void exportClassIssueAndRecommendation(HttpServletResponse response, Integer... ids) {
        if(ids.length>5){
            return;
        }
        List<ClassSuggest> classSuggests = classSuggestDao.queryClsSug(ids);
        //excel文件名
        String fileName = "班级问题和建议清单"+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = "班级问题与建议清单";

        //因为暂时不知道如何设置字间距，
        String headLine = "班 级 问 题 与 建 议 清 单" ;

        //excel标题
        String[] title = {SERIAL_NUMBER,QUESTION_OR_SUGGESTION,QUESTION_OR_SUGGESTION_TIME,SOLVE_AND_REPLY};

        String[][] values = new String[classSuggests.size()][4];

        for (int i = 0; i < classSuggests.size(); i++) {
            values[i] = new String[title.length];
            ClassSuggest obj = classSuggests.get(i);
            values[i][0] = i+1+"" ;
            values[i][1] = obj.getContent();
            values[i][2] = obj.getSuggesttime();
            values[i][3] = obj.getReply();
        }
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName,headLine,title,values);
        HSSFSheet sheet = wb.getSheet(sheetName);

        sheet.setColumnWidth(0,10*256);
        sheet.setColumnWidth(1,40*256);
        sheet.setColumnWidth(2,20*256);
        sheet.setColumnWidth(3,30*256);

        sheet.getRow(1).setHeight((short)(60*10));

        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 30);
        //加粗
        font.setBold(true);
        style.setFont(font);
        //垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // 创建一个居中格式
        style.setAlignment(CENTER);
        sheet.getRow(0).getCell(0).setCellStyle(style);
        for(int i = 2;i<2+classSuggests.size();i++){
            sheet.getRow(i).setHeight((short)(60*35));
        }

        CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,3);

        sheet.addMergedRegion(callRangeAddress);
        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);

        //响应到客户端
        setResponseHeader(response, fileName,wb);
    }

}
