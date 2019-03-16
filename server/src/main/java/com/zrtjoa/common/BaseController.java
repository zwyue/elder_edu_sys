package com.zrtjoa.common;

import com.zrtjoa.entity.Power;
import com.zrtjoa.entity.Teacher;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.zrtjoa.constant.SysConstant.*;

/**
 * BaseController class
 *
 * @author zwy
 * @date 2018/11/22 13:46
 */
public class BaseController {

    /**
     * 获取session用户
     *
     * @author zwy
     * @date 2018/11/29 15:59
     */
    protected Teacher getLoginUser(HttpSession httpSession){
        return (Teacher) httpSession.getAttribute(CACHE_USER);
    }

    /**
     * 获取缓存菜单
     *
     * @author zwy
     * @date 2019/3/15 21:22
     */
    protected List<Power> getMenuPower (HttpSession httpSession){
        return (List<Power>) httpSession.getAttribute(CACHE_MENU);
    }

    /**
     * 判断是否是管理员
     *
     * @author zwy
     * @date 2019/3/15 21:22
     */
    protected Boolean isAdmin (HttpSession httpSession){
        return (Boolean) httpSession.getAttribute(CACHE_ADMIN);
    }
}
