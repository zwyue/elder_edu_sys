package com.zrtjoa.util;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;

import static com.zrtjoa.constant.SysConstant.Punctuation.COMMA;
import static com.zrtjoa.constant.SysConstant.Punctuation.DOT;
import static org.apache.poi.xwpf.usermodel.TextAlignment.CENTER;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author zwy
 *     @date 2019/1/21 10:36
 *     email        1092478224@qq.com
 *     desc
 * </pre>
 */
public class WordDocument {

    public static XWPFDocument addImagesToWordDocument(File... imageFiles)throws Exception{
        XWPFDocument doc = new XWPFDocument();

        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun run = paragraph.createRun();

        //设置纸张方向
        CTDocument1 document = doc.getDocument();
        CTBody body = document.getBody();
        if (!body.isSetSectPr()) {
            body.addNewSectPr();
        }
        CTSectPr section = body.getSectPr();

        if(!section.isSetPgSz()) {
            section.addNewPgSz();
        }
        CTPageSz pageSize = section.getPgSz();
        //必须要设置下面两个参数，否则整个的代码是无效的
        pageSize.setW(BigInteger.valueOf(15840));
        pageSize.setH(BigInteger.valueOf(12240));
        pageSize.setOrient(STPageOrientation.LANDSCAPE);

        //设置页边距
        CTPageMar ctpagemar = section.addNewPgMar();
        ctpagemar.setLeft(BigInteger.valueOf(567));
        ctpagemar.setRight(BigInteger.valueOf(567));
        ctpagemar.setTop(BigInteger.valueOf(567));
        ctpagemar.setBottom(BigInteger.valueOf(567));

        for (File imageFile : imageFiles) {

            BufferedImage bimg = ImageIO.read(imageFile);
//            int width = bimg.getWidth();
//            int height = bimg.getHeight();
            String imgFile = imageFile.getName();
            int imgFormat = getImageFormat(imgFile);
            run.addPicture(new FileInputStream(imageFile), imgFormat, imgFile, Units.toEMU(712), Units.toEMU(544));
        }
        return doc ;
    }

    private static int getImageFormat(String imgFileName) {
        int format ;

        switch (imgFileName.substring(imgFileName.lastIndexOf(DOT))){
            case ".emf" : format = XWPFDocument.PICTURE_TYPE_EMF; break;
            case ".wmf" : format = XWPFDocument.PICTURE_TYPE_WMF; break;
            case ".png" : format = XWPFDocument.PICTURE_TYPE_PNG; break;
            case ".pict" : format = XWPFDocument.PICTURE_TYPE_PICT; break;
            case ".jpeg" : format = XWPFDocument.PICTURE_TYPE_JPEG; break;
            case ".jpg" : format = XWPFDocument.PICTURE_TYPE_JPEG; break;
            case ".dib" : format = XWPFDocument.PICTURE_TYPE_DIB; break;
            case ".tiff" : format = XWPFDocument.PICTURE_TYPE_TIFF; break;
            case ".gif" : format = XWPFDocument.PICTURE_TYPE_GIF; break;
            case ".eps" : format = XWPFDocument.PICTURE_TYPE_EPS; break;
            case ".wpg" : format = XWPFDocument.PICTURE_TYPE_WPG; break;
            case ".bmp" : format = XWPFDocument.PICTURE_TYPE_BMP; break;
            default:format = 0 ;
        }
        return format;
    }

    public static void main(String[] args) {
//        try {
//            addImagesToWordDocument(new File("C:\\zwy\\download\\timg.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        }
    }
}
