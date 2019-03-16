package com.zrtjoa.timeTask;

import com.zrtjoa.service.TermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    private TermService termService ;

    @Scheduled(cron = "0 0 1 1 * ?")//每月1号凌晨1点执行一次
    public void updateTermStatus(){
        try {
            //使学期过期
            termService.disableTerm();
            //使激活状态学期成为本学期
            termService.enableTerm();
        }catch (Exception e){
            logger.info("............e:{}",e);
        }
    }
}
