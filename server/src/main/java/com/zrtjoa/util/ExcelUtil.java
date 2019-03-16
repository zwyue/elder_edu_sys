package com.zrtjoa.util;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;

import static org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2019/1/7 9:10
 *     email        1092478224@qq.com
 *     desc         excel导出工具类
 * </pre>
 */
public class ExcelUtil {
    /**
     * 导出课表Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return HSSFWorkbook
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String headLine,
                                               String []title, String [][]values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        //设置高度
        row.setHeight((short)(30*20));

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();

        //垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // 创建一个居中格式
        style.setAlignment(CENTER);

        //声明列对象
        HSSFCell cell = null;

        //设置表格表头
        cell = row.createCell(0);
        cell.setCellValue(headLine);
        cell.setCellStyle(style);

        //设置一个边框
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        //自动换行
        style.setWrapText(true);

        HSSFFont font = wb.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 10);
        //选择需要用到的字体格式
        style.setFont(font);

        //创建标题
        row = sheet.createRow(1) ;
        row.setHeight((short)(20*20));;
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }


        //创建内容
        for(int i=1;i<=values.length;i++){
            row = sheet.createRow(i + 1);
            row.setHeight((short)(50*20));
            for(int j=0;j<values[i-1].length;j++){
                //将内容按顺序赋给对应的列对象
                cell = row.createCell(j);
                cell.setCellValue(values[i-1][j]);
                cell.setCellStyle(style);
            }
        }

        printPagePosition(sheet,false);

        return wb;
    }

    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String headLine,
                                               String []title, String [][]values){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        //设置高度
        row.setHeight((short)(60*20));

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();

        //垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // 创建一个居中格式
        style.setAlignment(CENTER);

        //声明列对象
        HSSFCell cell = null;

        HSSFFont font = wb.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 30);
        style.setFont(font);

        //设置表格表头
        cell = row.createCell(0);
        cell.setCellValue(headLine);
        cell.setCellStyle(style);

        //设置一个边框
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        //自动换行
        style.setWrapText(true);

        //设置字体大小
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);

        //创建标题
        row = sheet.createRow(1) ;
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=1;i<=values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i-1].length;j++){
                //将内容按顺序赋给对应的列对象
                cell = row.createCell(j);
                cell.setCellValue(values[i-1][j]);
                cell.setCellStyle(style);
            }
        }

        printPagePosition(sheet,false);

        return wb;
    }

    public static HSSFWorkbook getAttendanceSheet(String sheetName,String headLine,String classname,String tname,
                                               String []title, String [][]values){

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFRow row = sheet.createRow(0);

        //在 Excel 中，多个 cell 会共用一个 style，这样会就不必为每个 cell存储一个 style。
        // 所以虽然我们只想修改一个 cell 的 style，但其他 cell 也跟着变了
        HSSFCellStyle style = wb.createCellStyle();

        HSSFFont font = wb.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 17);
        style.setFont(font);
        // 创建一个居中格式
        style.setAlignment(CENTER);

        //声明列对象
        HSSFCell cell = null;

        //设置表格标题，即第一行
        cell = row.createCell(0);
        cell.setCellValue(headLine);
        cell.setCellStyle(style);

        //设置第二行，即总括信息样式
        HSSFCellStyle style1 = wb.createCellStyle();
        //font似乎也会覆盖
        HSSFFont font1 = wb.createFont();
        font1.setFontHeightInPoints((short) 13);
        style1.setFont(font1);
        // 创建一个左对齐格式
        style1.setAlignment(HorizontalAlignment.LEFT);

        // 总括信息左对齐
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("班级:"+classname);
        cell.setCellStyle(style1);
        row.createCell(1);
        row.createCell(2);
        row.createCell(3);
        row.createCell(4);
        cell = row.createCell(5);
        cell.setCellValue("任课教师:"+tname);
        cell.setCellStyle(style1);
        row.createCell(6);
        row.createCell(7);
        row.createCell(8);
        cell = row.createCell(9);
        cell.setCellValue("");
        cell.setCellStyle(style1);
        for(int i = 10 ;i<22;i++){
            row.createCell(i);
        }

        createTitleAndContent(wb,sheet,title,cell,values);

        printPagePosition(sheet,false);
        return wb;
    }

    private static void createTitleAndContent(HSSFWorkbook wb ,
                                       HSSFSheet sheet,String []title,
                                       HSSFCell cell ,String [][]values){
        // 设置值表头 设置表头左对齐
        HSSFCellStyle style = wb.createCellStyle();
        //font似乎也会覆盖
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);

        //设置一个边框
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(CENTER);

        //创建标题，即内容标题
        HSSFRow row = sheet.createRow(2) ;
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        for(int i=7;i<title.length+15;i++){
            cell = row.createCell(i);
            cell.setCellValue("");
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=2;i<=values.length+1;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i-2].length;j++){
                //将内容按顺序赋给对应的列对象
                cell = row.createCell(j);
                cell.setCellValue(values[i-2][j]);
                cell.setCellStyle(style);
            }
        }
    }

    /**
     * 打印格式
     *
     * @author zwy
     * @date 2019/1/10 18:28
     */
    private static void printPagePosition(HSSFSheet sheet,Boolean landscape){
        HSSFPrintSetup ps = sheet.getPrintSetup();

        // 打印方向，true：横向，false：纵向
        ps.setLandscape(landscape);
        //纸张
        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
        // 页边距（下）
        sheet.setMargin(HSSFSheet.BottomMargin,( double ) 0.5 );
        // 页边距（左）
        sheet.setMargin(HSSFSheet.LeftMargin,( double ) 0.1 );
        // 页边距（右）
        sheet.setMargin(HSSFSheet.RightMargin,( double ) 0.1 );
        // 页边距（上）
        sheet.setMargin(HSSFSheet.TopMargin,( double ) 0.5 );
        //设置打印页面为水平居中
        sheet.setHorizontallyCenter(true);
        //设置打印页面为垂直居中使用POI输出Excel时打印页面的设置
        // - TOUGHGUYNEU - @EXPLORER使用POI输出Excel时打印页面的设置
        // - TOUGHGUYNEU - @EXPLORER
//        sheet.setVerticallyCenter(true);
    }

    /**
     * 导出Excel
     *
     * @param sheetName sheet名称
     * @param title     标题
     * @param values    内容
     * @param wb        HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook wb, HttpServletRequest request) throws IOException {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }


        /*生成图表*/
        String imgUrl = request.getParameter("base64Info");
        if(!StringUtils.isEmpty(imgUrl)) {
            String[] imgUrlArr = imgUrl.split("base64,");//拆分base64编码后部分

            byte[] buffer = new BASE64Decoder().decodeBuffer(imgUrlArr[1]);
            //记住系统如果为Linux，此处路径需改
            String picPath = "C:\\pic\\pic" + ".png";
            File file = new File(picPath);//图片文件

            try {
                //生成图片
                OutputStream out = new FileOutputStream(file);//图片输出流
                out.write(buffer);
                out.flush();//清空流
                out.close();//关闭流
                ByteArrayOutputStream outStream = new ByteArrayOutputStream(); // 将图片写入流中
                BufferedImage bufferImg = ImageIO.read(new File(picPath));
                ImageIO.write(bufferImg, "PNG", outStream);
                // 利用HSSFPatriarch将图片写入EXCEL
                HSSFPatriarch patri = sheet.createDrawingPatriarch();
                HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 0, 15, (short) 6, 35);
                patri.createPicture(anchor, wb.addPicture(outStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (file.exists()) {
                file.delete();//删除图片
            }
        }

        return wb;
    }

    public static HSSFWorkbook meetRecordExcel(String sheetName, String headLine, String secondLine, String thirdLine, String[] title, String[][] values) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short)(56*10));

        //在 Excel 中，多个 cell 会共用一个 style，这样会就不必为每个 cell存储一个 style。
        // 所以虽然我们只想修改一个 cell 的 style，但其他 cell 也跟着变了
        HSSFCellStyle style = wb.createCellStyle();

        //设置一个边框
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        HSSFFont font = wb.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 13);
        style.setFont(font);
        // 创建一个居中格式
        style.setAlignment(CENTER);
        //垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFFont font1 = wb.createFont();
        //设置字体大小
        font1.setFontHeightInPoints((short) 18);
        font1.setFontName("黑体");
        font1.setBold(true);
        style1.setFont(font1);
        // 创建一个居中格式
        style1.setAlignment(CENTER);

        //声明列对象
        HSSFCell cell = null;

        //设置表格标题，即第一行
        cell = row.createCell(0);
        cell.setCellValue(headLine);
        cell.setCellStyle(style1);

        //创建第二行
        row = sheet.createRow(1);
        row.setHeight((short)(56*10));
        cell = row.createCell(0);
        cell.setCellValue(secondLine);
        cell.setCellStyle(style1);

        //创建第三行
        row = sheet.createRow(2);
        row.setHeight((short)(56*10));
        cell = row.createCell(0);
        cell.setCellValue(thirdLine);

        //设置第三行样式，即班级信息样式，因为样式会覆盖，所以需要新建样式
        HSSFCellStyle style2 = wb.createCellStyle();
        //font似乎也会覆盖
        HSSFFont font2 = wb.createFont();
        font2.setFontHeightInPoints((short) 18);
        font2.setFontName("黑体");
        font2.setBold(true);
        style2.setFont(font2);
        // 创建一个左对齐格式
        style2.setAlignment(HorizontalAlignment.LEFT);
        cell.setCellStyle(style2);

        //创建标题
        row = sheet.createRow(3) ;
        row.setHeight((short)(65*10));
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 4);
            row.setHeight((short)(65*10));
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                cell = row.createCell(j);
                cell.setCellValue(values[i][j]);
                cell.setCellStyle(style);
            }
        }
        printPagePosition(sheet,false);
        return wb ;
    }
}
