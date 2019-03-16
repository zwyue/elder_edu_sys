package com.zrtjoa.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.zrtjoa.common.ResultUtils.isChinese;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/25 16:40
 *     email        1092478224@qq.com
 *     desc
 * </pre>
 */
public class TjoaExceptionResolver implements HandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(TjoaExceptionResolver.class);

    /**
     * 系统抛出的异常
     * @author zwy
     * @param request 请求
     * @param response 回应
     * @param handler 处理器
     * @param ex 异常
     * @return 错误
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        logger.info("============出现异常===========");

        //解析出异常类型
        TjoaException tjoaException ;

        // 若该异常类型是系统自定义的异常，直接取出异常信息在错误页面展示即可。
        if(ex instanceof TjoaException ){

            logger.info("============系统异常============");

            tjoaException = (TjoaException) ex;
        }else {

            logger.info("============非系统异常===========");

            // 如果不是系统自定义异常，构造一个系统自定义的异常类型，信息为“未知错误”
            tjoaException = new TjoaException(ex.getMessage());
        }

        logger.info("异常信息:",ex);

        //错误信息
        String message = tjoaException.getMessage();

        if(!isChinese(message)){
            message = "系统错误！";
        }
        ModelAndView modelAndView = new ModelAndView();
        //将错误信息传到页面
        modelAndView.addObject("message",message);
        //指向错误页面
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
