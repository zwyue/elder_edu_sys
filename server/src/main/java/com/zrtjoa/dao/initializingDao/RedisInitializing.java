package com.zrtjoa.dao.initializingDao;

import com.zrtjoa.dao.*;
import com.zrtjoa.entity.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.stream.Collectors;

import static com.zrtjoa.constant.SysConstant.Admin.CALL_NAME;
import static com.zrtjoa.constant.SysConstant.Admin.NAME;
import static com.zrtjoa.constant.SysConstant.INIT_PASSWORD;
import static com.zrtjoa.constant.SysConstant.Punctuation.COMMA;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/22 12:19
 *     email        1092478224@qq.com
 *     desc
 * </pre>
 */
@Component
public class RedisInitializing implements InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger(RedisInitializing.class);

    @Autowired
    private RedisTemplate redisTemplate ;

    @Autowired
    private CategoryDao categoryDao ;

    @Autowired
    private ProfessionDao professionDao ;

    @Autowired
    private TermDao termDao ;

    @Autowired
    private RosterDao rosterDao ;

    @Value("${redis.host}")
    private String redis_host ;

    @Value("${redis.port}")
    private Integer redis_port ;

    @Override
    public void afterPropertiesSet() throws Exception {

        Teacher sysTeacher = (Teacher) redisTemplate.opsForValue().get("sysTeacher");


        //连接redis
        Jedis  redis = new Jedis (redis_host,redis_port);
        redis.flushAll();

        //存储redis管理员
        if(sysTeacher==null){
            sysTeacher = new Teacher();
            sysTeacher.setPassword(INIT_PASSWORD);
            sysTeacher.setTname(CALL_NAME);
            sysTeacher.setTnumber(NAME);
        }

        logger.info(".......存储管理员密码........");

        redisTemplate.opsForValue().set("sysTeacher",sysTeacher);

        logger.info(".......初始化存储当前激活状态学期........");

        //初始化存储当前激活状态学期
        Term activeTerm = termDao.queryActiveTerm();
        redisTemplate.opsForValue().set("activeTerm",activeTerm);

        //初始化存储当前学期
        Term thisTerm = termDao.queryThisTerm();
        redisTemplate.opsForValue().set("thisTerm",thisTerm);

        logger.info("..........初始化存储学生学号信息.........");

        //在花名册表中根据学期查
        List<String> stuNo = rosterDao.queryStuNo();

        //存入缓存
        stuNo.forEach(no->{
            List<String> keys = Arrays.asList(no.split(COMMA));
            keys.forEach(key->{
                key = key.substring(0,10) ;
                List classifyNos = (List) redisTemplate.opsForValue().get(key);
                classifyNos = classifyNos==null ? new ArrayList() : classifyNos ;
                classifyNos.add(no.substring(10,12));
                List sortList = (List) classifyNos.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
                redisTemplate.opsForValue().set(key,sortList);
            });
        });

        //查询类别存入到redis
        List<Category> categories = categoryDao.getcatelist();
        redisTemplate.opsForValue().set("categories",categories);

        //查询专业存入到redis
        List<Profession> professions = professionDao.getprolist(null);
        redisTemplate.opsForValue().set("professions",professions);
    }
}
