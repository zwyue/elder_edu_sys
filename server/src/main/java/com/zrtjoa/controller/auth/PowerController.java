package com.zrtjoa.controller.auth;

import com.zrtjoa.common.BaseController;
import com.zrtjoa.common.ResultUtils;
import com.zrtjoa.entity.Power;
import com.zrtjoa.entity.Teacher;
import com.zrtjoa.service.PowerService;
import com.zrtjoa.vo.PowerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zrtjoa.constant.SysConstant.MAP_DEFAULT_SIZE;

/**
 * copyright    <a href="http://www.qaqavr.com"/>中锐</a>
 * <pre>
 *     @author     zwy
 *     @date       2018/12/1 15:27
 *     email       1092478224@qq.com
 *     desc         权限管理
 * </pre>
 */
@RestController
@RequestMapping("/power")
public class PowerController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(PowerController.class);

    @Autowired
    private PowerService powerService ;

    @RequestMapping("/powers")
    @POST
    public Map powers() {
        List<PowerVo> powerVos = powerService.queryPowerList(null);
        return ResultUtils.success(powerVos);
    }

    /**
     * 获取权限菜单
     *
     * @author zwy
     * @date 2019/1/2 13:27
     */
    @RequestMapping("/query-menu")
    @GET
    public Map queryMenu(HttpSession httpSession){
        logger.info(".....登陆后请求菜单");
        Map<String,Object> map = new HashMap<>(MAP_DEFAULT_SIZE);
        map.put("menu",getMenuPower(httpSession));
        Teacher teacher = new Teacher();
        if(isAdmin(httpSession)){
            teacher.setTname("管理员");
        }else {
            teacher = getLoginUser(httpSession);
        }
        map.put("user",teacher);
        return ResultUtils.success(map);
    }
}
