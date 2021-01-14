package com.zhu.base.service.impl;

import com.zhu.base.annotation.PagingQuery;
import com.zhu.base.constant.SysConstant;
import com.zhu.base.service.MeetRecordService;
import com.zhu.base.dao.MeetRecordDao;
import com.zhu.base.entity.MeetRecord;
import com.zhu.base.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.zhu.base.util.FileUtil.setResponseHeader;

/**
 * @Author: yangli
 * @Description:
 * @Date: Created in 15:32 2018/12/26
 * @Modified by:
 */
@Service
public class MeetRecordServiceImpl implements MeetRecordService {

    @Autowired
    private MeetRecordDao meetRecordDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return meetRecordDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MeetRecord record) {
        return meetRecordDao.insert(record);
    }

    @Override
    public int insertSelective(MeetRecord record) {
        return meetRecordDao.insertSelective(record);
    }

    @Override
    public MeetRecord selectByPrimaryKey(Integer id) {
        return meetRecordDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MeetRecord record) {
        return meetRecordDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MeetRecord record) {
        return meetRecordDao.updateByPrimaryKey(record);
    }

    @Override
    @PagingQuery
    public List<MeetRecord> getList(Map map) {
        return meetRecordDao.getList(map);
    }

    @Override
    @PagingQuery
    public List<MeetRecord> getLists() {
        return meetRecordDao.getLists();
    }

    @Override
    @PagingQuery
    public List<MeetRecord> getTitleList(String content) {
        return meetRecordDao.getTitleList(content);
    }

    @Override
    @PagingQuery
    public List<MeetRecord> getTitleLists(Map map) {
        return meetRecordDao.getTitleLists(map);
    }

    @Override
    public void exportMeetRecord(HttpServletResponse response, Integer... ids) {
        if(ids.length>20){
            return;
        }
        List<MeetRecord> meetRecords = meetRecordDao.queryRecordByIds(ids);

        //excel文件名
        String fileName = "班会记录"+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = "班会记录";

        //因为暂时不知道如何设置字间距，
        String headLine = "班   会   记   录" ;
        String secondLine = "(上学期  每班一页)" ;
        String thirdLine = meetRecords.size()==0? "班级：":"班级：" + meetRecords.get(0).getClassname() ;

        //excel标题
        String[] title = {SysConstant.SheetTitle.WEEK_SERIAL, SysConstant.SheetTitle.DATE, SysConstant.SheetTitle.TOPIC, SysConstant.SheetTitle.REMARKS};

        String[][] values = new String[20][4];

        for (int i = 0; i < 20; i++) {

            values[i] = new String[4];
            String weekSort = "第"+(i+1)+"周" ;
            values[i][0] = weekSort ;
            values[i][1] = "";
            values[i][2] = "";
            values[i][3] = "";
            for (MeetRecord meetRecord : meetRecords) {
                if(meetRecord.getWeeksort().equals(weekSort)){
                    values[i][1] = meetRecord.getMeettime();
                    values[i][2] = meetRecord.getContent();
                    values[i][3] = meetRecord.getRemark();
                }
            }
        }
        HSSFWorkbook wb = ExcelUtil.meetRecordExcel(sheetName,headLine,secondLine,thirdLine,title,values);
        HSSFSheet sheet = wb.getSheet(sheetName);

        sheet.setColumnWidth(0,9*256);
        sheet.setColumnWidth(1,15*256);
        sheet.setColumnWidth(2,55*256);
        sheet.setColumnWidth(3,15*256);

        CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,3);

        sheet.addMergedRegion(callRangeAddress);
        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderBottom(BorderStyle.NONE,callRangeAddress,sheet);

        callRangeAddress = new CellRangeAddress(1,1,0,3);
        sheet.addMergedRegion(callRangeAddress);
        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderBottom(BorderStyle.NONE,callRangeAddress,sheet);

        callRangeAddress = new CellRangeAddress(2,2,0,3);
        sheet.addMergedRegion(callRangeAddress);
        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);

        //响应到客户端
        setResponseHeader(response, fileName,wb);
    }
}
