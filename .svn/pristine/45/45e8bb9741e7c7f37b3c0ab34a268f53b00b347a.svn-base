package com.zrtjoa.service.impl;

import com.zrtjoa.annotation.PagingQuery;
import com.zrtjoa.common.ResultUtils;
import com.zrtjoa.constant.SysConstant;
import com.zrtjoa.dao.StudentEnterDao;
import com.zrtjoa.dao.TermDao;
import com.zrtjoa.entity.StudentEnter;
import com.zrtjoa.entity.Teacher;
import com.zrtjoa.entity.Term;
import com.zrtjoa.enums.StatusEnum;
import com.zrtjoa.service.TermService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zrtjoa.common.TimeUtil.compareDate;
import static com.zrtjoa.common.TimeUtil.plusYear;
import static com.zrtjoa.constant.SysConstant.TermStatus.THIS_TERM;

/**
 * TermServiceImpl class
 *
 * @author zwy
 * @date 2018/11/29 14:48
 */
@Service
public class TermServiceImpl implements TermService{

    @Autowired
    private TermDao termDao ;

    @Resource(name = "transactionTemplate")
    private TransactionTemplate transactionTemplate ;

    @Autowired
    private RedisTemplate redisTemplate ;

    @Autowired
    private StudentEnterDao studentEnterDao ;

    /**
     * 新建学期，复制学期
     *
     * @author zwy
     * @date 2018/11/29 16:08
     */
    @Override
    public Integer createNewTerm(Term term) {
        //初始化学期为不可用状态,即未激活状态
        term.setIsenable(SysConstant.TermStatus.DISABLE_TERM);
        //校验日期
        validateDate(term);

        int i = termDao.insert(term) ;

        if(i>0 && term.getIsenable().equals(THIS_TERM)){
            redisTemplate.opsForValue().set("thisTerm",termDao.queryThisTerm());
        }
        return i ;
    }

    @Override
    public Integer disableTerm() {
        return termDao.disableTerm();
    }

    @Override
    public Integer enableTerm() {
        int i = termDao.enableTerm();
        Term term = termDao.queryThisTerm();
        redisTemplate.opsForValue().set("thisTerm",term);
        return i ;
    }

    @Override
    @PagingQuery
    public List<Term> queryTermList() {
        List<Term> terms = termDao.queryTermList();
        terms.forEach(term -> term.setIsEnableTxt(StatusEnum.returnEnumByCode(term.getIsenable()).msg));
        return terms ;
    }

    @Override
    public Map updateTermStatus() {

        Map map = transactionTemplate.execute(new TransactionCallback<Map>() {
            @Override
            public Map doInTransaction(TransactionStatus status) {
                Integer disableItems = termDao.disableTerm();
                Integer enableItems = termDao.enableTerm();
                Map map = new HashMap(134);
                map.put("disableItems",disableItems);
                map.put("enableItems",enableItems);
                return map;
            }
        });
        return map;
    }

    @Override
    public Map updateTerm(Term term) {
        //校验日期
        Term oldTerm = termDao.selectByPrimaryKey(term.getId());
        if(oldTerm.getIsenable().equals("0")||oldTerm.getIsenable().equals("3")){
            return ResultUtils.error("该学期无法修改");
        }
        if(oldTerm.getIsenable().equals("1")){
            //查询该学期有无学生
            List<StudentEnter> studentEnters = studentEnterDao.queryEnterByTermId(term.getId());
            if(studentEnters!=null&&studentEnters.size()>0){
                return ResultUtils.error("该学期无法修改");
            }
        }
        return ResultUtils.success(termDao.updateByPrimaryKey(term));
    }

    @Override
    public Map deleteTerm(List<Integer> idList) {
        //因为当前只是单条删除，所以可以允许如下写法
        //查询该学期状态
        StringBuilder stringBuilder = new StringBuilder();
        idList.forEach(id->{
            Term term = termDao.selectByPrimaryKey(id);
            if(term.getIsenable().equals("0")||term.getIsenable().equals("3")){
               stringBuilder.append("该学期无法删除");
               return;
            }
            if(term.getIsenable().equals("1")){
                //查询该学期有无学生
                List<StudentEnter> studentEnters = studentEnterDao.queryEnterByTermId(id);
                if(studentEnters!=null&&studentEnters.size()>0){
                    stringBuilder.append("该学期无法删除");
                    return;
                }
            }
        });
        stringBuilder.append("删除成功");
        return ResultUtils.success(stringBuilder);
    }

    @Override
    public Integer copyTerm(Integer id, Teacher teacher) {
        Term oldTerm = termDao.selectByPrimaryKey(id);
        Assert.notNull(oldTerm);
        oldTerm.setIsenable(SysConstant.TermStatus.DISABLE_TERM);
        oldTerm.setUsername(teacher.getTname());
        oldTerm.setUserid(teacher.getId());
        oldTerm.setStarttime(plusYear(oldTerm.getStarttime(),1));
        oldTerm.setEndtime(plusYear(oldTerm.getEndtime(),1));
        return createNewTerm(oldTerm);
    }

    @Override
    public Term queryThisTerm() {
        return termDao.queryActiveTerm();
    }

    @Override
    public Term queryTermById(Integer id) {
        return termDao.selectByPrimaryKey(id);
    }

    @Override
    public Map activeTerm(Integer id) {
        //查询是否有激活状态的学期
        Term term = termDao.queryActiveTerm();
        if(term==null){
            int i = termDao.enableTermById(id);
            if(i>0){
                //将激活学期替换redis当前激活学期
                term = termDao.queryActiveTerm();
                redisTemplate.opsForValue().set("activeTerm",term);
                return ResultUtils.success("激活成功");
            }
        }
        //查询激活状态下的学期是否有学生报名
        List<StudentEnter> studentEnters = studentEnterDao.queryEnterByTermId(term.getId());
        if(studentEnters!=null&&studentEnters.size()>0){
            return ResultUtils.error("当前已有激活状态的学期,且该学期已报名学生,无法激活学期") ;
        }
        return ResultUtils.error("已存在激活状态的学期，请先禁用已激活学期") ;
    }

    @Override
    public Map disableTermById(Integer id) {
        //查询激活状态下的学期是否有学生报名
        List<StudentEnter> studentEnters = studentEnterDao.queryEnterByTermId(id);
        if(studentEnters!=null&&studentEnters.size()>0){
            return ResultUtils.error("该激活状态学期已报名学生,无法禁用学期") ;
        }
        int i = termDao.disableTermById(id);
        if(i==1){
            redisTemplate.delete("activeTerm");
        }
        return ResultUtils.success(i==1?"禁用成功":"无法禁用") ;
    }

    @Override
    public Map ifTermActive() {
        //查询是否有激活状态的学期
        Term term = termDao.queryActiveTerm();
        if(term==null){
            return ResultUtils.error("无激活状态学期，请先激活学期");
        }
        return ResultUtils.success() ;
    }

    /**
     * 日期校验
     *
     * @author zwy
     * @date 2018/11/30 14:29
     * @param term
     */
    private void validateDate(Term term){
        String startTime = term.getStarttime();
        String endTime = term.getEndtime() ;

        //时间判断
        Assert.notNull(startTime,"开始日期不能为空");
        Assert.notNull(endTime,"结束日期不能为空");

        Assert.isTrue(compareDate(endTime,startTime),"结束日期应大于开始日期");

        //查询上学期
        Term lastTerm = termDao.selectLastTerm(term);

        if (lastTerm==null){
            //如果当前没有学期，则新建必须新建当前学期，当前日期应处于开始和结束日期当中
            Assert.isTrue(compareDate(endTime, LocalDate.now().toString()),"结束日期应大于当前日期");
            Assert.isTrue(compareDate(LocalDate.now().toString(),startTime),"当前日期应大于开始日期");
            term.setIsenable(THIS_TERM);
        }else {
            Assert.isTrue(compareDate(startTime,lastTerm.getEndtime()),"开始日期应大于上学期结束时间");
        }
    }
}
