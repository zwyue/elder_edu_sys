package com.zhu.base.service.impl;

import com.zhu.base.annotation.PagingQuery;
import com.zhu.base.constant.SysConstant;
import com.zhu.base.service.TeacherService;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.dao.ClassesDao;
import com.zhu.base.dao.TeacherDao;
import com.zhu.base.entity.Classes;
import com.zhu.base.entity.Profession;
import com.zhu.base.entity.Teacher;
import com.zhu.base.enums.StatusEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

import static com.zhu.base.util.ObectUtils.assembleCtgrPrfsCls;

/**
 * TeacherServiceImpl class
 *
 * @author zwy
 * @date 2018/11/28 13:13
 */
@Service
public class TeacherServiceImpl implements TeacherService, InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    private static StringBuilder lastTNumber = new StringBuilder() ;

    @Autowired
    private TeacherDao teacherDao ;

    @Autowired
    private RedisTemplate redisTemplate ;

    @Autowired
    private ClassesDao classesDao ;

    /**
     * 根据教师编号查询教师
     *
     * @author zwy
     * @date 2018/11/28 13:19
     */
    @Override
    public Teacher queryTeacherByNumber(String tnumber) {
        return teacherDao.queryTeacherByNumber(tnumber);
    }

    /**
     * 根据教师id查询教师
     *
     * @author zwy
     * @date 2018/12/11 16:41
     */
    @Override
    public Teacher queryTeacherById(Integer userId) {
        Teacher teacher = teacherDao.selectByPrimaryKey(userId);
        if(teacher!=null){
            //根据专业id查询专业类别
            List<Profession> professions = (List<Profession>) redisTemplate.opsForValue().get("professions");
            String majorid = teacher.getMajorid();
            StringBuilder sbIds = new StringBuilder();
            StringBuilder sbNames = new StringBuilder();
            if(StringUtils.isNotBlank(majorid)){
                Arrays.asList(majorid.split(SysConstant.Punctuation.COMMA)).forEach(m->{
                    professions.forEach(p->{
                        if(p.getId() == Integer.parseInt(m)){
                            sbIds.append(p.getCateid()).append(SysConstant.Punctuation.COMMA);
                            sbNames.append(p.getCatename()).append(SysConstant.Punctuation.COMMA);
                        }
                    });
                });
                logger.info("分类id为：{}",sbIds);
                logger.info("分类名称为：{}",sbNames);
                teacher.setCateId(sbIds.substring(0,sbIds.lastIndexOf(SysConstant.Punctuation.COMMA)));
                teacher.setCateName(sbNames.substring(0,sbNames.lastIndexOf(SysConstant.Punctuation.COMMA)));
            }
        }
        return teacher;
    }

    /**
     * 更新教师信息
     *
     * @author zwy
     * @date 2018/12/11 16:42
     */
    @Override
    public Integer updateTeacher(Teacher teacher) {
        return teacherDao.updateByPrimaryKeySelective(teacher);
    }

    /**
     * 查询全部教师（条件）
     *
     * @author zwy
     * @date 2018/12/11 16:42
     * @param name 姓名
     * @param idCard 身份证号
     */
    @Override
    @PagingQuery
    public List<Teacher> queryAllTeacher(String name, String idCard) {
        Map<String,String> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
        map.put("name",name);
        map.put("idCard",idCard);
        List<Teacher> teachers = teacherDao.queryAllTeacher(map);
        teachers.forEach(teacher -> teacher.setStatusTxt(StatusEnum.returnEnumByCode(teacher.getStatus()).msg));
        return teachers ;
    }

    /**
     * 根据角色id查询拥有该角色的用户
     *
     * @author zwy
     * @date 2018/12/11 18:00
     */
    @Override
    public List<Teacher> queryTeacherByRoleId(Integer roleId) {
        return teacherDao.queryTeacherByRoleId(roleId);
    }

    /**
     * 插入教师信息（当前支持的教师编号最多四位）
     *
     * @author zwy
     * @date 2018/12/20 14:10
     */
    @Override
    public Integer enterTeacher(Teacher teacher) {
        //根据身份证号校验是否存在该教师
        Integer ifTeacherExist = teacherDao.queryTeacherByIdCard(teacher.getSfzh());
        if(ifTeacherExist>0){
            return ifTeacherExist ;
        }
        //生成教师编号
        int a = Integer.parseInt(lastTNumber.toString());
        Integer b = a + 1 ;
        String c = b.toString();
        teacher.setTnumber(c.length()==1?"000"+c:c.length()==2?"00"+c:c.length()==3?"0"+c:c);
        Integer ifSuccess = teacherDao.insert(teacher);

        //如果插入成功则更新缓存
        if(ifSuccess>0){

            try {
                afterPropertiesSet();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return ifTeacherExist ;
    }

    /**
     * 删除教师信息
     *
     * @author zwy
     * @date 2018/12/20 18:14
     */
    @Override
    public Integer deleteTeacher(Integer id){
        Integer ifDelete = teacherDao.deleteByPrimaryKey(id);
        if(ifDelete>0){

            try {
                afterPropertiesSet();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return ifDelete;
    }

    @Override
    public List<Teacher> getteaList(Integer majorid) {
        return teacherDao.getteaList(majorid);
    }

    @Override
    public Integer teacherBindClass(Integer teacherId, String classIds,String classNames) {
        Map<String,String> map = assembleCtgrPrfsCls(classIds,classNames);

        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        teacher.setMajorid(map.get("majorIds"));
        teacher.setMajorname(map.get("majorNames"));
        teacher.setClassid(map.get("clsIds"));
        teacher.setClassname(map.get("clsNames"));

        return teacherDao.updateByPrimaryKeySelective(teacher);
    }

    @Override
    public Map<String,Object> ifHasBindCls(Integer teacherId) {
        Teacher teacher = teacherDao.selectByPrimaryKey(teacherId);
        String clsId = teacher.getClassid();

        List<String> clsNameList = new ArrayList<>();
        List<String> clsIdList = new ArrayList<>();

        if(StringUtils.isNotBlank(clsId)){
            Arrays.asList(clsId.split(SysConstant.Punctuation.COMMA)).forEach(clazzId->{
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

        Map<String,Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
        if(clsIdList.size()!=0){
            map.put("value",clsIdList.toArray());
            map.put("name",clsNameList.toArray());
            return map ;
        }
        return null ;
    }


    @Override
    public Map updatePsw(Teacher teacher, String newPsw,HttpSession session) {

        boolean isAdmin = (Boolean) session.getAttribute(SysConstant.CACHE_ADMIN);

        if(isAdmin){
            Teacher sysTeacher = (Teacher) redisTemplate.opsForValue().get("sysTeacher");
            sysTeacher.setPassword(newPsw);
            redisTemplate.opsForValue().set("sysTeacher",sysTeacher);
            session.invalidate();
            return ResultUtils.success("密码修改成功");
        }

        teacher.setPassword(newPsw);
        int i = updateTeacher(teacher);
        if(i>0){
            session.invalidate();
            return ResultUtils.success("密码修改成功");
        }
        return ResultUtils.error("密码修改失败");
    }

    @Override
    public void afterPropertiesSet(){

        logger.info("...........初始化查询数据库最后的教师编号..........");

        //查询数据库最后的教师编号
        String lastTnum = teacherDao.queryLastTnumber();
        //清空缓存教师编号
        lastTNumber.setLength(0);
        //加入新的教师编号
        lastTNumber.append(StringUtils.isNotBlank(lastTnum)?lastTnum:"00");
    }
}
