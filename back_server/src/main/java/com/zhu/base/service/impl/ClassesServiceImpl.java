package com.zhu.base.service.impl;

import com.zhu.base.annotation.PagingQuery;
import com.zhu.base.constant.SysConstant;
import com.zhu.base.entity.*;
import com.zhu.base.service.CategoryService;
import com.zhu.base.service.ClassesService;
import com.zhu.base.service.TeacherService;
import com.zhu.base.dao.ClassesDao;
import com.zhu.base.dao.RosterDao;
import com.zrtjoa.entity.*;
import com.zhu.base.util.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static com.zhu.base.common.TimeUtil.calAge;
import static com.zhu.base.util.FileUtil.setResponseHeader;

/**
 * 班级管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class ClassesServiceImpl implements ClassesService {

    private final static Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    @Autowired
    private ClassesDao classesDao;

    @Autowired
    private RosterDao rosterDao ;

    @Autowired
    private RedisTemplate redisTemplate ;

    @Autowired
    private CategoryService categoryService ;

    @Autowired
    private TeacherService teacherService ;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return classesDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Classes record) {
        return classesDao.insert(record);
    }

    @Override
    public int insertSelective(Classes record) {
        return classesDao.insertSelective(record);
    }

    @Override
    public Map queryClassInfo(Integer id) {
        Classes clazz = classesDao.selectByPrimaryKey(id);
        List<Category> categories = categoryService.cateHasCls();
        // just for finding teacher's headmaster role
        Teacher teacher = teacherService.queryTeacherById(clazz.getHeadmaster());
        List<Teacher> headmasters = new ArrayList<>();
        // Due to one user just can be bound one role ,here can be coded like this
        if(teacher!=null){
            headmasters = teacherService.queryTeacherByRoleId(Integer.parseInt(teacher.getRoleid()));
        }
        Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
        map.put("classInfo",clazz);
        map.put("cateInfo",categories);
        map.put("headmasters",headmasters);
        return map ;
    }

    @Override
    public int updateByPrimaryKeySelective(Classes record) {
        return classesDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Classes record) {
        return classesDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Classes> getList() {
        return classesDao.getList();
    }

    @Override
    public List<Classes> byNameList(String name) {
        return classesDao.byNameList(name);
    }

    @Override
    public List<Classes> getCList(Integer majorid) {
        return classesDao.getCList(majorid);
    }

    @Override
    public List<Classes> getTeaClasslist(Integer tid) {
        return classesDao.getTeaClasslist(tid);
    }

    @Override
    public List<Classes> getTeaClasslists(Integer headmaster) {
        return classesDao.getTeaClasslists(headmaster);
    }

    @Override
    public void exportTeacherAndCadre(HttpServletResponse response, Classes classes) {

        List<Classes> teacherAndCadres =  classesDao.queryClasses(classes);
        //excel文件名
        String fileName = "科任教师、班干部名单"+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = "科任教师、班干部名单";

        //excel标题
        String[] title = {SysConstant.SheetTitle.SERIAL_NUMBER, SysConstant.SheetTitle.CLAZZ, SysConstant.SheetTitle.TEACHER, SysConstant.SheetTitle.MONITOR, SysConstant.SheetTitle.STUDY_COMMITTEE, SysConstant.SheetTitle.SAFE_COMMITTEE, SysConstant.SheetTitle.CONTACT, SysConstant.SheetTitle.REMARKS};

        String[][] values = new String[teacherAndCadres.size()][];

        for (int i = 0; i < teacherAndCadres.size(); i++) {
            values[i] = new String[title.length];
            Classes obj = teacherAndCadres.get(i);
            values[i][0] = i+1+"" ;
            values[i][1] = obj.getClassname();
            values[i][2] = obj.getTname();
            values[i][3] = obj.getMonitor();
            values[i][4] = obj.getStudyer();
            values[i][5] = obj.getSafer();
            values[i][6] = obj.getPhone();
            values[i][7] = "";
        }
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName,"科任教师、班干部名单",title,values);
        HSSFSheet sheet = wb.getSheet(sheetName);
        sheet.setColumnWidth(6,15*256);
        CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,7);
        sheet.addMergedRegion(callRangeAddress);
        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);

        //响应到客户端
        setResponseHeader(response, fileName,wb);
    }

    @Override
    public void exportCheckInForm(HttpServletResponse response, Integer classesId,Integer termId) {

        Map<String,Integer> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE) ;
        map.put("classId",classesId);
        map.put("termId",termId);

        List<Roster> rosters = rosterDao.queryRosterByClassIdAndTerm(map);

        //excel文件名
        String fileName = "考勤表"+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = "考勤表";

        String year = rosters.get(0).getCrttime().substring(0,4);
        Integer month = Integer.parseInt(rosters.get(0).getCrttime().substring(6,7));
        String termname;
        if(month>7){
            termname="春季";
        }else{
            termname="秋季";
        }
        String headLine = year+"年天津市南开区老年大学"+termname+"考勤表"+"";
        String classname = rosters.get(0).getClassname();
        Classes classes = classesDao.selectByPrimaryKey(rosters.get(0).getClassid());
        String tname = classes.getTname();

        //excel标题
        String[] title = {SysConstant.SheetTitle.SERIAL_NUMBER, SysConstant.SheetTitle.STUDENT_ID, SysConstant.SheetTitle.NAME, SysConstant.SheetTitle.SEX, SysConstant.SheetTitle.AGE, SysConstant.SheetTitle.PHONE, SysConstant.SheetTitle.FAM_PHONE};

        int sizes = rosters.size()+1;
        String[][] values = new String[rosters.size()][22];

        logger.info("string[][]的长度：",values[0].length);

        for (int i = 0; i < rosters.size(); i++) {
            values[i] = new String[22];
            Roster obj = rosters.get(i);
            String birthDate = obj.getBirthdate();
            values[i][0] = i+1+"" ;
            values[i][1] = obj.getStunumber();
            values[i][2] = obj.getStuname();
            values[i][3] = obj.getSex();
            values[i][4] = calAge(birthDate)+"";
            values[i][5] = obj.getPhone();
            values[i][6] = obj.getFamPhone();
            for (int j = 7 ;j<22 ;j ++ ){
                values[i][j] = "" ;
            }
        }

        HSSFWorkbook wb = ExcelUtil.getAttendanceSheet(sheetName,headLine,classname,tname,title,values);
        HSSFSheet sheet = wb.getSheet(sheetName);

        sheet.setColumnWidth(0,5*256);
        sheet.setColumnWidth(1,8*256);
        sheet.setColumnWidth(2,8*256);
        sheet.setColumnWidth(3,5*256);
        sheet.setColumnWidth(4,5*256);
        sheet.setColumnWidth(5,14*256);
        sheet.setColumnWidth(6,14*256);
        for(int i = 7 ;i<22;i++){
            sheet.setColumnWidth(i,3*256);
        }

        CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,21);
        sheet.addMergedRegion(callRangeAddress);
        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);

        callRangeAddress = new CellRangeAddress(1,1,0,4);
        sheet.addMergedRegion(callRangeAddress);
        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);

        callRangeAddress = new CellRangeAddress(1,1,5,8);
        sheet.addMergedRegion(callRangeAddress);
        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);

        callRangeAddress = new CellRangeAddress(1,1,9,21);
        sheet.addMergedRegion(callRangeAddress);
        RegionUtil.setBorderLeft(BorderStyle.NONE,callRangeAddress,sheet);
        RegionUtil.setBorderTop(BorderStyle.NONE,callRangeAddress,sheet);

        //响应到客户端
        setResponseHeader(response, fileName,wb);
    }

    @Override
    public List<Classes> queryClsByCateAndPrf(Integer cateId, Integer prfId) {
        Map map = new HashMap() ;
        map.put("cateId",cateId);
        map.put("prfId",prfId);
        List<Classes> classes = classesDao.queryClassesByCateIdAndPrfId(map);
        return classes;
    }

    @Override
    public List<Map<String,Object>> queryClsCateToAcquireCls() {
        List<Category> categories = (List<Category>) redisTemplate.opsForValue().get("categories");
        List<Profession> professions = (List<Profession>) redisTemplate.opsForValue().get("professions");
        List<Map<String,Object>> cateMapList = new ArrayList<>();
        categories.forEach(category -> {
            Map<String,Object> cateMap = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
            //过滤出该分类下的专业
            List<Profession> profs = professions.stream().filter(profession->category.getId()
                    .equals(profession.getCateid())).collect(Collectors.toList());
            List<Map<String,Object>> prfMapList = new ArrayList<>();
            profs.forEach(profession -> {
                //根据专业id查询班级
                List<Classes> classes = classesDao.getCList(profession.getId());
                List<Map<String,Object>> clsMapList = new ArrayList<>();
                Map<String,Object> prfMap = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
                //只有班级存在的专业才会放进分类
                if(classes.size()!=0){
                    classes.forEach(c->{
                        Map<String,Object> clsMap = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
                        clsMap.put("name",c.getClassname());
                        clsMap.put("value",c.getId());
                        clsMap.put("children",new ArrayList<>());
                        clsMapList.add(clsMap);
                    });
                    prfMap.put("name",profession.getMajorname());
                    prfMap.put("value",profession.getId());
                    prfMap.put("children",clsMapList);
                    prfMapList.add(prfMap);
                }
            });
            //班级存在的实质性专业存在时，该分类才有实质性，即有意义的，需要被呈现的
            if(prfMapList.size()!=0){
                cateMap.put("name",category.getCategory());
                cateMap.put("value",category.getId());
                cateMap.put("children",prfMapList);
                cateMapList.add(cateMap);
            }
        });
        return cateMapList ;
    }

    @Override
    public List<Classes> queryClsByPrfs(String prfIds) {
        //根据专业id查询班级
        return classesDao.getClsByPrfIds(Arrays.asList(prfIds.split(SysConstant.Punctuation.COMMA)));
    }

    @Override
    @PagingQuery
    public List<Classes> queryClass(String className) {
        Classes classes = new Classes();
        classes.setClassname(className);
        return classesDao.queryClasses(classes);
    }

    @Override
    public Integer addNewCls(Classes classes) {
        List<Classes> list = classesDao.getCList(classes.getMajorid());
        int number = 0 ;
        if(list!=null && list.size()!=0){
            String num = list.stream().filter(l-> StringUtils.isNotBlank(l.getNumber()))
                    .max(Comparator.comparing(Classes::getNumber)).get().getNumber();
            number = Integer.parseInt(num==null?"0":num);
        }
        classes.setNumber(String.valueOf(++number));
        return classesDao.insertSelective(classes);
    }

    @Override
    public List<Classes> queryClassInfoByIds(List<String> classIds) {
        return classesDao.queryClsByIds(classIds);
    }
}
