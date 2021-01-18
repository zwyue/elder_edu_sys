package com.zhu.base.controller.auth;

import com.zhu.base.common.BaseController;
import com.zhu.base.common.ResultUtils;
import com.zhu.base.entity.Teacher;
import com.zhu.base.service.PowerService;
import com.zhu.base.vo.PowerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhu.base.constant.SysConstant.MAP_DEFAULT_SIZE;

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

    @PostMapping("/powers")
    public Map<String, Object> powers() {
        List<PowerVo> powerVos = powerService.queryPowerList(null);
        return ResultUtils.success(powerVos);
    }

    /**
     * 获取权限菜单
     *
     * @author zwy
     * @date 2019/1/2 13:27
     */
    @GetMapping("/query-menu")
    public Map<String, Object> queryMenu(HttpSession httpSession){
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
