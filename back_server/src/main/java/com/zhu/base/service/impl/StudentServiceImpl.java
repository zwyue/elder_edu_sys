package com.zhu.base.service.impl;

import com.github.pagehelper.PageInfo;
import com.zhu.base.annotation.PagingQuery;
import com.zhu.base.constant.SysConstant;
import com.zhu.base.entity.*;
import com.zhu.base.exception.ExceptionEnum;
import com.zhu.base.service.StudentService;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.dao.ClassesDao;
import com.zhu.base.dao.RosterDao;
import com.zhu.base.dao.StudentDao;
import com.zhu.base.dao.StudentEnterDao;
import com.zhu.base.dto.StuAndStuEnter;
import com.zrtjoa.entity.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.zhu.base.common.TimeUtil.calculateAge;
import static com.zhu.base.util.ObectUtils.assembleCtgrPrfsCls;

/**
 * 学生管理
 * @author yangli
 * @date 2018/12/25
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return studentDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Student record) {
        return studentDao.insert(record);
    }

    @Override
    public int insertSelective(Student record) {
        return studentDao.insertSelective(record);
    }

    @Override
    public Student selectByPrimaryKey(Integer id) {
        //只查当前学期的
        Student student = studentDao.selectByPrimaryKey(id);
        student.setAge(calculateAge(student.getSfzh().substring(6,14)));
        return student;
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return studentDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return studentDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Student> getStudentList() {
        return studentDao.getStudentList();
    }

    @Override
    public List<Student> getSList(Integer classid) {
        return studentDao.getSList(classid);
    }

    @Override
    public List<Student> getNameList(String name) {
        return studentDao.getNameList(name);
    }

    /**
     * copyright    <a href="http://www.qaqavr.com/>中锐</a>
     * <pre>
     *     author        zwy
     *     @date        2018/12/21 9:26
     *     email        1092478224@qq.com
     *     desc         学生管理
     * </pre>
     */

    private final static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    /**
     * 报名信息表
     *
     * @date 2018/12/24 17:15
     */
    @Autowired
    private StudentEnterDao studentEnterDao ;

    /**
     * 学生花名册表
     *
     * @date 2018/12/24 17:16
     */
    @Autowired
    private RosterDao rosterDao ;

    @Autowired
    private TransactionTemplate transactionTemplate ;

    @Autowired
    private RedisTemplate redisTemplate ;

    @Autowired
    private ClassesDao classesDao ;

    /**
     * 学生信息录入
     *
     *  todo - 校验报名班级时间是否冲突
     *
     * @author zwy
     * @date 2018/12/21 9:28
     */
    @Override
    public Map enterStudentInfo(Student student, StudentEnter studentEnter, String flag) {
        //获取可用学期
        Term term = new Term();
        if("1".equals(flag)){
            term = (Term) redisTemplate.opsForValue().get("activeTerm");
            if(term==null){
                return ResultUtils.error("当前无可用学期");
            }
        }else {
            term = (Term) redisTemplate.opsForValue().get("thisTerm");
            if(term==null){
                return ResultUtils.error("当前无可用学期");
            }
        }
        //校验学生年龄
        Integer age = calculateAge(student.getSfzh().substring(6,14));

        if(age>80){
            return ResultUtils.error("超过最高限制年龄80岁");
        }

        studentEnter.setTermId(term.getId());
        studentEnter.setTermName(term.getTerm());

        //查询学生是否存在
        Student existStudent = studentDao.queryStudentByIdCard(student.getSfzh());

        if("2".equals(flag)&&existStudent!=null){
            return ResultUtils.error("该学生已存在！");
        }

        if(existStudent!=null){
            //查询是否重复报名，即所报班级中是否已存在该学生id
            //可以报同一专业的不同班级
            Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
            map.put("existStu", existStudent.getId());
            map.put("termId", term.getId());
            StudentEnter registerStu = studentEnterDao.selectByStuIdAndTermId(map);
            //删除该学期该学生报名信息
            if(registerStu!=null){
                List<Roster> rosters = rosterDao.queryRosterByStuIdAndTermId(map);
                int i =transactionTemplate.execute(del->{
                    int delete = 0 ;
                    delete+=rosterDao.delByStuIdAndTermId(map);
                    delete+=studentEnterDao.deleteByStuIdAndTermId(map);
                    return delete ;
                });
                if (i>0){
                    //删除redis缓存
                    rosters.forEach(roster -> {
                        List<String> nums = (List<String>) redisTemplate.opsForValue().get(roster.getStunumber().substring(0,10));
                        if(nums!=null) {
                            nums.removeIf(n -> n.equals(roster.getStunumber().substring(10, 12)));
                            redisTemplate.opsForValue().set(roster.getStunumber().substring(0, 10), nums);
                        }
                    });
                }
            }
        }

        Map<String,String> ctgrPrfsCls = assembleCtgrPrfsCls(student.getClassid(),student.getClassname());
        studentEnter.setClassid(ctgrPrfsCls.get("clsIds"));
        studentEnter.setClassname(ctgrPrfsCls.get("clsNames"));
        studentEnter.setCateid(ctgrPrfsCls.get("cateIds"));
        studentEnter.setCatename(ctgrPrfsCls.get("cateNames"));
        studentEnter.setMajorid(ctgrPrfsCls.get("majorIds"));
        studentEnter.setMajorname(ctgrPrfsCls.get("majorNames"));
        student.setClassid("");
        student.setClassname("");
        student.setMajorName("");
        student.setCateName("");

        //报名几个班
        Map<String,Object> results = formatStuNum(Arrays.asList(studentEnter.getClassid().split(SysConstant.Punctuation.COMMA)));

        Map<String,String> map = (Map<String, String>) results.get("map");

        //获取以逗号隔开的学号
        String studentNumbers = (String) results.get("string");

        if(existStudent!=null){
            //根据学生id和截取的学号查询学生报名信息
            Map<String,String> newMap = new HashMap<>();
            map.keySet().forEach(key->{
                Map<String,Object> queryMap = new HashMap();
                queryMap.put("stuId",existStudent.getId());
                queryMap.put("subStuNumber",key.substring(4,10));
                Map stuRoster = rosterDao.queryRosterBySubStuNumAndStuId(queryMap);
                if(stuRoster!=null){
                    newMap.put(String.valueOf(stuRoster.get("stunumber")),stuRoster.get("classname")+ SysConstant.Punctuation.COMMA+stuRoster.get("classId"));
                }else {
                    newMap.put(key,map.get(key));
                }
            });
            map.clear();
            map.putAll(newMap);
            StringBuilder stringBuilder = new StringBuilder();
            map.keySet().forEach(key->{
                stringBuilder.append(key).append(SysConstant.Punctuation.COMMA);
            });
        }else {
            student.setClassid(ctgrPrfsCls.get("clsIds"));
            student.setClassname(ctgrPrfsCls.get("clsNames"));
            student.setMajorName(ctgrPrfsCls.get("majorNames"));
            student.setCateName(ctgrPrfsCls.get("cateNames"));
            student.setStunumber(studentNumbers);
        }

        student.setAge(age);

        Roster roster = new Roster();
        roster.setStuname(student.getStuname());
        roster.setTermid(term.getId());
        roster.setTermname(term.getTerm());
        roster.setBirthdate(student.getSfzh().substring(6,14));
        roster.setAge(age);
        roster.setPhone(student.getPhone());
        roster.setFamPhone(student.getEmergency());

        return startToEnter(existStudent,student,roster,map,studentEnter);
    }

    @Override
    @PagingQuery
    public List<Student> queryAllStudents(String name, String idCard,String status) {
        logger.info("......查询学生信息.......");
        Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
        map.put("name",name);
        map.put("idCard",idCard);
        Term term = (Term) redisTemplate.opsForValue().get("thisTerm");

        if("1".equals(status)){
            term = (Term) redisTemplate.opsForValue().get("activeTerm");
        }

        //查询当前学期的所有学生
        map.put("termId",term==null?0:term.getId());
        List<Student> students = studentDao.queryAllStudents(map);
        return studentSetCls(students);
    }

    private List<Student> studentSetCls(List<Student> students){
        students.forEach(student -> {
            //学生年龄
            student.setAge(calculateAge(student.getSfzh().substring(6,14)));

            //查询班级
            List<Classes> classes = classesDao.queryClsByIds(Arrays.asList(student.getClassid().split(SysConstant.Punctuation.COMMA)));
            StringBuilder headMaster = new StringBuilder() , majorName = new StringBuilder() , cateName = new StringBuilder();

            //所以班级不能随便删除，后面修改吧todo
            if(classes.size()!=0) {
                classes.forEach(cls->{
                    headMaster.append(cls.getHeadmastername()).append(SysConstant.Punctuation.COMMA);
                    majorName.append(cls.getMajorname()).append(SysConstant.Punctuation.COMMA);
                    cateName.append(cls.getCatename()).append(SysConstant.Punctuation.COMMA);
                });
                //学生班主任
                student.setHeadMasterName(headMaster.substring(0,headMaster.lastIndexOf(SysConstant.Punctuation.COMMA)));
                //专业、类别
                student.setMajorName(majorName.substring(0,majorName.lastIndexOf(SysConstant.Punctuation.COMMA)));
                student.setCateName(cateName.substring(0,cateName.lastIndexOf(SysConstant.Punctuation.COMMA)));
            }
        });
        return students ;
    }

    @Override
    public Integer updateStudentInfo(Student student) {

        logger.info("........更新学生信息........");

        return studentDao.updateByPrimaryKeySelective(student);
    }

    @Override
    public Integer deleteStuInfo(Integer stuId) {
        return transactionTemplate.execute(delete->{
            //查询该生基本信息
            Student student = studentDao.selectByPrimaryKey(stuId);

            //删除学生信息
            int isDel = studentDao.deleteByPrimaryKey(stuId);
            isDel += studentEnterDao.deleteByStuId(stuId);
            isDel += rosterDao.delByStuId(stuId);

            //删除redis缓存信息
            Arrays.asList(student.getStunumber().split(SysConstant.Punctuation.COMMA)).forEach(number->{
                List<String> nums = (List<String>) redisTemplate.opsForValue().get(number.substring(0,10));
                if(nums!=null){
                    nums.removeIf(n->n.equals(number.substring(10,12)));
                    redisTemplate.opsForValue().set(number.substring(0,10),nums);
                }
            });
            return isDel ;
        });
    }

    @Override
    public Map<String,Object> updateEnterInfo(StudentEnter studentEnter) {
        return transactionTemplate.execute(trans->{
            Integer isUpdate = 0 ;
            if(StringUtils.isNotBlank(studentEnter.getClassid())){
                //查询该生基本信息
                Student student = studentDao.selectByPrimaryKey(studentEnter.getStuid());
                //查询该生原来的报名信息，对比报名班级是否改变
                StudentEnter originEnter = studentEnterDao.queryEnterByEnterId(studentEnter.getId());
                if(!studentEnter.getClassid().equals(originEnter.getClassid())){
                    //删除redis学号信息和花名册信息
                    String stringStuNos = student.getStunumber();
                    Arrays.asList(stringStuNos.split(SysConstant.Punctuation.COMMA)).forEach(number->{
                        List<String> nums = (List<String>) redisTemplate.opsForValue().get(number.substring(0,10));
                        nums.removeIf(n->n.equals(number.substring(10,12)));
                        redisTemplate.opsForValue().set(number.substring(0,10),nums);
                        rosterDao.delByStuNo(number);
                    });

                    Map results = formatStuNum(Arrays.asList(studentEnter.getClassid().split(SysConstant.Punctuation.COMMA)));
                    Map<String,String> classInfo = (Map<String, String>) results.get("map");
                    //插入学生信息的学生编号，以逗号隔开
                    String studentNumbers = (String) results.get("string");
                    //修改学生信息表
                    Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
                    map.put("studentNumbers",studentNumbers);
                    map.put("className",studentEnter.getClassname());
                    map.put("classId",studentEnter.getClassid());
                    map.put("id",studentEnter.getStuid());
                    isUpdate += studentDao.updateStudentNumbers(map);
                    //删除原有花名册学生信息
                    isUpdate += rosterDao.deleteByClassIdAndStuId(Arrays.asList(stringStuNos.split(SysConstant.Punctuation.COMMA)));

                    //新增花名册信息
                    Roster roster = new Roster();
                    roster.setStuid(originEnter.getStuid());
                    roster.setStuname(originEnter.getStuname());
                    roster.setAge(student.getAge());
                    roster.setBirthdate(student.getSfzh().substring(6,14));
                    redisTemplateInsert(classInfo,roster);
                }
            }
            isUpdate += studentEnterDao.updateByPrimaryKeySelective(studentEnter);
            if(isUpdate>0){
                return ResultUtils.success(ExceptionEnum.SUCCESS) ;
            }
            return ResultUtils.error(ExceptionEnum.UPDATE_FAILED.errorCode, ExceptionEnum.UPDATE_FAILED.errorMessage);
        });
    }

    @Override
    public Map queryStuAllInfo(Integer id,String idCard) {

        Term term = (Term) redisTemplate.opsForValue().get("thisTerm");

        Student student = new Student();
        if(id!=null){
            student = studentDao.selectByPrimaryKey(id);
        }
        if(StringUtils.isNotBlank(idCard)){
            student = studentDao.queryStudentByIdCard(idCard);
        }
        if(student!=null && StringUtils.isNotBlank(student.getSfzh())){
            student.setAge(calculateAge(student.getSfzh().substring(6,14)));
        }
        //报名信息（当前学期）
        Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
        map.put("termId",term.getId());
        map.put("stuId",id);
        StudentEnter studentEnter = studentEnterDao.queryEnterByStuIdThisTerm(map);

        //根据专业id查询专业类别
        List<String> clsNameList = new ArrayList<>();
        List<String> clsIdList = new ArrayList<>();

        if(studentEnter!=null && StringUtils.isNotBlank(studentEnter.getClassid())){
            Arrays.asList(studentEnter.getClassid().split(SysConstant.Punctuation.COMMA)).forEach(clazzId->{
                StringBuilder clsIds = new StringBuilder();
                StringBuilder clsNames = new StringBuilder();
                Classes classes = classesDao.selectByPrimaryKey(Integer.parseInt(clazzId));
                clsIds.append(classes.getCateid()).append(SysConstant.Punctuation.SLASH)
                        .append(classes.getMajorid()).append(SysConstant.Punctuation.SLASH)
                        .append(classes.getId());
                clsIdList.add(clsIds.toString());
                clsNames.append(classes.getCatename()).append(SysConstant.Punctuation.SLASH)
                        .append(classes.getMajorname()).append(SysConstant.Punctuation.SLASH)
                        .append(classes.getClassname());
                clsNameList.add(clsNames.toString());
            });
        }

        if(clsIdList.size()!=0){
            map.put("value",clsIdList.toArray());
            map.put("name",clsNameList.toArray());
        }
        map.put("baseInfo",student);
        map.put("enterInfo",studentEnter);
        return map ;
    }

    @Override
    public Map update(StuAndStuEnter stuAndStuEnter,String flag) {
        //更新基本信息
        Student student = new Student();
        student.setId(stuAndStuEnter.getId());
        student.setAddress(stuAndStuEnter.getAddress());
        student.setPhone(stuAndStuEnter.getPhone());
        student.setCarnumber(stuAndStuEnter.getCarnumber());
        student.setEmergency(stuAndStuEnter.getEmergency());
        student.setStuname(stuAndStuEnter.getStuname());
        student.setEntertime(stuAndStuEnter.getEntertime());
        student.setStuname(stuAndStuEnter.getStuname());
        student.setGraduatetime(stuAndStuEnter.getGraduatetime());

        //todo-目前是修改当前学生报名信息
        Term term = (Term) redisTemplate.opsForValue().get("thisTerm");

        StudentEnter studentEnter = new StudentEnter();
        studentEnter.setStuid(stuAndStuEnter.getId());
        studentEnter.setFee(stuAndStuEnter.getFee());
        studentEnter.setIscard(stuAndStuEnter.getIscard());
        studentEnter.setStuname(stuAndStuEnter.getStuname());
        if(stuAndStuEnter.getTermId()==null){
            studentEnter.setTermId(term.getId());
            studentEnter.setTermName(term.getTerm());
        }else {
            studentEnter.setTermId(stuAndStuEnter.getTermId());
            studentEnter.setTermName(stuAndStuEnter.getTermName());
        }

        String[] clsIds = new String[]{};
        String[] orginClassIds = new String[]{};
        Map<String,String> ctgrPrfsCls = new HashMap<>();
        if(StringUtils.isNotBlank(stuAndStuEnter.getClassid())&&StringUtils.isNotBlank(stuAndStuEnter.getClassname())){
            clsIds = new String[stuAndStuEnter.getClassid().split(SysConstant.Punctuation.COMMA).length];
            for (int i = 0; i <stuAndStuEnter.getClassid().split(SysConstant.Punctuation.COMMA).length; i++) {
                clsIds[i] = stuAndStuEnter.getClassid().split(SysConstant.Punctuation.COMMA)[i].split(SysConstant.Punctuation.SLASH)[2];
            }
            String originClsIds = studentDao.selectByPrimaryKey(stuAndStuEnter.getId()).getClassid();
            orginClassIds = originClsIds.split(SysConstant.Punctuation.COMMA);
            ctgrPrfsCls = assembleCtgrPrfsCls(stuAndStuEnter.getClassid(),stuAndStuEnter.getClassname());
        }

        StringBuilder willDelIds = new StringBuilder();
        StringBuilder willAddIds = new StringBuilder() ;
        StringBuilder willContain = new StringBuilder() ;

        List x = Arrays.asList(orginClassIds);
        List y = Arrays.asList(clsIds);

        x.forEach(x1->{
            if(y.contains(x1)){
                willContain.append(x1).append(SysConstant.Punctuation.COMMA);
            }
            else {
                willDelIds.append(x1).append(SysConstant.Punctuation.COMMA);
            }
        });

        y.forEach(y1->{
            if(!x.contains(y1)){
                willAddIds.append(y1).append(SysConstant.Punctuation.COMMA);
            }
        });

        List<String> stuDelNumbers =new ArrayList<>();
        List<String> stuNumbers = new ArrayList<>();
        List<String> addNoToRedis = new ArrayList<>();
        Map info = new HashMap();

        if(StringUtils.isNotBlank(willAddIds.toString())||StringUtils.isNotBlank(willDelIds.toString())){
            studentEnter.setClassid(ctgrPrfsCls.get("clsIds"));
            studentEnter.setClassname(ctgrPrfsCls.get("clsNames"));
            studentEnter.setCateid(ctgrPrfsCls.get("cateIds"));
            studentEnter.setCatename(ctgrPrfsCls.get("cateNames"));
            studentEnter.setMajorid(ctgrPrfsCls.get("majorIds"));
            studentEnter.setMajorname(ctgrPrfsCls.get("majorNames"));

            if("1".equals(flag)){
                student.setClassid(ctgrPrfsCls.get("clsIds"));
                student.setClassname(ctgrPrfsCls.get("clsNames"));
                student.setMajorName(ctgrPrfsCls.get("majorNames"));
                student.setCateName(ctgrPrfsCls.get("cateNames"));
            }

            if(StringUtils.isNotBlank(willDelIds.toString())){
                List<String> copyNum = new ArrayList<>();
                Map map = new HashMap();
                map.put("stuId",stuAndStuEnter.getId());
                map.put("clsIds",willDelIds.toString().split(SysConstant.Punctuation.COMMA));
                copyNum = rosterDao.queryStuNumByStuIdAndClsIds(map);
                stuDelNumbers.addAll(copyNum);

            }
            if(StringUtils.isNotBlank(willContain.toString())){
                Map map = new HashMap();
                map.put("stuId",stuAndStuEnter.getId());
                map.put("clsIds",willContain.toString().split(SysConstant.Punctuation.COMMA));
                stuNumbers = rosterDao.queryStuNumByStuIdAndClsIds(map);
            }
            String studentNumbers = "" ;

            for (String stoNo: stuNumbers) {
                studentNumbers += stoNo + SysConstant.Punctuation.COMMA ;
            }

            if(StringUtils.isNotBlank(willAddIds.toString())){
                Map results = formatStuNum(Arrays.asList(willAddIds.toString().split(SysConstant.Punctuation.COMMA)));
                info.put("map",results.get("map"));
                //插入学生信息的学生编号，以逗号隔开
                addNoToRedis.addAll(Arrays.asList(((String) results.get("string")).split(SysConstant.Punctuation.COMMA)));
                studentNumbers += (String) results.get("string");
            }

            //修改学生信息表
            if(stuAndStuEnter.getTermId()==null){
                student.setStunumber(studentNumbers);
            }
        }
        //更新报名信息
        return transactionTemplate.execute(update->{
            if(stuDelNumbers.size()!=0){
                stuDelNumbers.forEach(number->{
                    List<String> nums = (List<String>) redisTemplate.opsForValue().get(number.substring(0,10));
                    if(nums!=null){
                        nums.removeIf(n->n.equals(number.substring(10,12)));
                        redisTemplate.opsForValue().set(number.substring(0,10),nums);
                        rosterDao.delByStuNo(number);
                    }
                });
            }
            if(addNoToRedis.size()!=0){
                //查询原有学生信息
                Student forRoster = studentDao.selectByPrimaryKey(stuAndStuEnter.getId());
                //插入花名册信息
                Roster roster = new Roster();
                roster.setStuid(forRoster.getId());
                roster.setStuname(forRoster.getStuname());
                if(stuAndStuEnter.getTermId()!=null){
                    roster.setTermid(stuAndStuEnter.getTermId());
                    roster.setTermname(stuAndStuEnter.getTermName());
                }else {
                    roster.setTermid(term.getId());
                    roster.setTermname(term.getTerm());
                }
                roster.setPhone(stuAndStuEnter.getPhone());
                roster.setFamPhone(stuAndStuEnter.getEmergency());
                roster.setBirthdate(forRoster.getSfzh().substring(6,14));
                roster.setAge(calculateAge(forRoster.getSfzh().substring(6,14)));
                Map<String,String> map = (Map<String, String>) info.get("map");
                redisTemplateInsert(map,roster);
            }
            //根据学生id.......及学期id(我感觉非常重要)........查询报名信息，后面出bug再改吧
//            StudentEnter forEnterInfo = studentEnterDao.queryEnterByStuId(stuAndStuEnter.getId());
            //更新报名信息(根据学生id)
            studentEnterDao.updateByStuId(studentEnter);
            //更新学生信息
            studentDao.updateByPrimaryKeySelective(student);
            if(stuAndStuEnter.getTermId()==null){
                return ResultUtils.success(null,"修改成功") ;
            }
            return ResultUtils.success(null,"报名成功") ;
        });
    }

    /**
     * redis存储
     *
     * @author zwy
     * @date 2018/12/28 9:47
     */
    private void redisTemplateInsert(Map<String,String> map,Roster roster){
        //插入花名册
        map.keySet().forEach(key->{
            roster.setStunumber(key);
            roster.setClassname(map.get(key).substring(0,map.get(key).lastIndexOf(SysConstant.Punctuation.COMMA)));
            roster.setClassid(Integer.parseInt(map.get(key).substring(map.get(key).lastIndexOf(SysConstant.Punctuation.COMMA)+1)));
            rosterDao.insert(roster);
            //更新缓存
            String no = key.substring(0,10) ;
            List classifyNos = (List) redisTemplate.opsForValue().get(no);
            classifyNos = classifyNos==null ? new ArrayList() : classifyNos ;
            classifyNos.add(key.substring(10,12));
            List sortList = (List) classifyNos.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            redisTemplate.opsForValue().set(no,sortList);
        });
    }

    /**
     * 格式化学生学号
     *
     * @author zwy
     * @date 2018/12/26 11:35
     */
    private Map<String,Object> formatStuNum(List<String> classIds){
        //缓存中的专业信息
        List<Profession> professions = (List<Profession>) redisTemplate.opsForValue().get("professions");

        Map<String,Object> results = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);

        Map<String,String> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);

        //插入学生信息的学生编号，以逗号隔开
        StringBuilder studentNumbers = new StringBuilder();

        classIds.forEach(cls->{
            //查询分类、专业编号
            Classes classes = classesDao.selectByPrimaryKey(Integer.parseInt(cls));
            professions.forEach(p -> {
                if(p.getId().equals(classes.getMajorid())){
                    //对于一个专业来说，只会有一个分类id吧？不管了，这里我只当成一个来写
                    String cateNum = p.getNumbers().length()== 2 ? p.getNumbers() : "0" + p.getNumbers();
                    String majorNum = p.getMajornumber().length() == 2 ? p.getMajornumber() : "0" + p.getMajornumber() ;
                    //班级编号，注意此处不是班级id
                    String classNum = classes.getNumber().length() == 2 ? classes.getNumber() : "0" + classes.getNumber() ;
                    //学号组成 年份 + 分类 + 专业 + 班级
                    String prefixNo = LocalDate.now().getYear() + cateNum + majorNum + classNum ;

                    List stuNos = (List) redisTemplate.opsForValue().get(prefixNo);
                    stuNos = stuNos!=null ? stuNos : new ArrayList();

                    String lastNo = "01" ;

                    if(stuNos.size()!=0){
                        lastNo =(String) stuNos.get(0);
                        int no = Integer.parseInt(lastNo) + 1 ;
                        lastNo = no/10 == 0?"0"+no:""+no ;
                    }
                    //学号为key,班级名加班级id为value
                    map.put(prefixNo+lastNo,classes.getClassname() + SysConstant.Punctuation.COMMA + classes.getId());
                    studentNumbers.append(prefixNo).append(lastNo).append(SysConstant.Punctuation.COMMA);
                }
            });
        });
        results.put("map",map);
        //以逗号连接学号
        results.put("string",studentNumbers.substring(0,studentNumbers.lastIndexOf(SysConstant.Punctuation.COMMA)));
        return results ;
    }

    /**
     * 报名开始
     *
     * @author zwy
     * @date 2018/12/26 11:35
     */
    private Map startToEnter(Student existStudent
            ,Student student
            ,Roster roster
            ,Map<String,String> map
            ,StudentEnter studentEnter){

        return transactionTemplate.execute(status->{
            if(existStudent==null){
                //插入学生信息
                studentDao.insert(student);
                studentEnter.setStuid(student.getId());
                //设置花名册学生id
                roster.setStuid(student.getId());
            }else {
                student.setId(existStudent.getId());
                //更新学生信息(学号覆盖)
                studentDao.updateByPrimaryKeySelective(student);
                studentEnter.setStuid(existStudent.getId());
                //更新花名册学生id
                roster.setStuid(existStudent.getId());
            }
            //插入学生报名信息
            studentEnterDao.insert(studentEnter);
            //插入花名册
            redisTemplateInsert(map,roster);
            return ResultUtils.success(ExceptionEnum.SUCCESS);
        });
    }
}
