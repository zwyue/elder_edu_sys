package com.zhu.base.service.impl;

import com.zhu.base.annotation.PagingQuery;
import com.zhu.base.constant.SysConstant;
import com.zhu.base.entity.StudentEnter;
import com.zhu.base.service.TermService;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.dao.StudentEnterDao;
import com.zhu.base.dao.TermDao;
import com.zhu.base.entity.Term;
import com.zhu.base.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.zhu.base.common.TimeUtil.compareDate;

/**
 * TermServiceImpl class
 *
 * @author zwy
 * @date 2018/11/29 14:48
 */
@Service
public class TermServiceImpl implements TermService {

    @Autowired
    private TermDao termDao ;

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
    public void createNewTerm(Term term) {
        //初始化学期为不可用状态,即未激活状态
        term.setIsenable(SysConstant.TermStatus.DISABLE_TERM);
        //校验日期
        validateDate(term);

        int i = termDao.insert(term) ;

        if(i>0 && term.getIsenable().equals(SysConstant.TermStatus.THIS_TERM)){
            redisTemplate.opsForValue().set("thisTerm",termDao.queryThisTerm());
        }
    }

    @Override
    public void disableTerm() {
        termDao.disableTerm();
    }

    @Override
    public Term enableTerm() {
        //更新为本学期
        termDao.enableTerm();
        return termDao.queryThisTerm();
    }

    @Override
    @PagingQuery
    public List<Term> queryTermList() {
        List<Term> terms = termDao.queryTermList();
        terms.forEach(term -> term.setIsEnableTxt(StatusEnum.returnEnumByCode(term.getIsenable()).msg));
        return terms ;
    }

    @Override
    public Map updateTerm(Term term) {
        //校验日期
        Term oldTerm = termDao.selectByPrimaryKey(term.getId());
        if(SysConstant.TermStatus.THIS_TERM.equals(oldTerm.getIsenable())||oldTerm.getIsenable().equals(SysConstant.TermStatus.PASS_DATE_TERM)){
            return ResultUtils.error("该学期无法修改");
        }
        if(SysConstant.TermStatus.ACTIVE_TERM.equals(oldTerm.getIsenable())){
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
            if(SysConstant.TermStatus.THIS_TERM.equals(term.getIsenable())|| SysConstant.TermStatus.PASS_DATE_TERM.equals(term.getIsenable())){
               stringBuilder.append("该学期无法删除");
               return;
            }
            if(SysConstant.TermStatus.ACTIVE_TERM.equals(term.getIsenable())){
                //查询该学期有无学生
                List<StudentEnter> studentEnters = studentEnterDao.queryEnterByTermId(id);
                if(studentEnters!=null&&studentEnters.size()>0){
                    stringBuilder.append("该学期无法删除");
                }
            }
        });
        stringBuilder.append("删除成功");
        return ResultUtils.success(stringBuilder);
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
            term.setIsenable(SysConstant.TermStatus.THIS_TERM);
        }else {
            Assert.isTrue(compareDate(startTime,lastTerm.getEndtime()),"开始日期应大于上学期结束时间");
        }
    }
}
