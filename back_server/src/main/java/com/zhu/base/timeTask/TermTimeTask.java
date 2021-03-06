package com.zhu.base.timeTask;

import com.zhu.base.dao.RosterDao;
import com.zhu.base.dao.StudentDao;
import com.zhu.base.dao.StudentEnterDao;
import com.zhu.base.entity.Roster;
import com.zhu.base.entity.StudentEnter;
import com.zhu.base.entity.Term;
import com.zhu.base.service.TermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhu.base.constant.SysConstant.MAP_DEFAULT_SIZE;
import static com.zhu.base.constant.SysConstant.Punctuation.COMMA;

/**
 * TermTimeTask class
 * 定时任务
 *
 * @author zwy
 * @date 2018/11/29 17:13
 */
@Component
public class TermTimeTask {

    private static final Logger logger = LoggerFactory.getLogger(TermTimeTask.class);

    @Autowired
    private TransactionTemplate transactionTemplate ;

    @Autowired
    private TermService termService ;

    @Autowired
    private StudentEnterDao studentEnterDao ;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private RosterDao rosterDao ;

    @Autowired
    private RedisTemplate redisTemplate ;

    @Scheduled(cron = "0 0 1 1 * ?")//每月1号凌晨1点执行一次
    public void updateTermStatus(){
        logger.info(".....start to update term status......");
        try {
            transactionTemplate.execute(update->{
                //使学期过期
                logger.info("......使学期过期......");
                termService.disableTerm();

                //使激活状态学期成为本学期
                logger.info(".......激活学期.......");
                Term term = termService.enableTerm();

                //更新学生本学期班级
                logger.info(".......更新学生本学期班级，学号");
                List<StudentEnter> studentEnters = studentEnterDao.queryEnterByTermId(term.getId());

                List<Roster> rosters = rosterDao.queryRosterByTermId(term.getId());

                //更新学生信息
                studentEnters.forEach(studentEnter -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    rosters.forEach(roster -> {
                        if(roster.getStuid().equals(studentEnter.getStuid())){
                            stringBuilder.append(roster.getStunumber()).append(COMMA);
                        }
                    });
                    Map<String,Object> map = new HashMap<>(MAP_DEFAULT_SIZE);
                    map.put("studentNumbers",stringBuilder.substring(0,stringBuilder.lastIndexOf(COMMA)));
                    map.put("className",studentEnter.getClassname());
                    map.put("classId",studentEnter.getClassid());
                    map.put("id",studentEnter.getStuid());
                    studentDao.updateStudentNumbers(map);
                });
                redisTemplate.opsForValue().set("thisTerm",term);
                return 0 ;
            });
        }catch (Exception e){
            logger.info("............e:{}",e);
        }
    }
}
