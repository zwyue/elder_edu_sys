package com.zhu.base.common;

import com.zhu.base.constant.SysConstant;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ly
 */
public class ResultUtils {

    private static int SUCCESS = 1;
    private static int ERROR = 0;

    private static String CODE = "code";
    private static String DATA = "data";
    private static String MSG = "msg";

    private static String PIC = "pic" ;
    private static String PIC_LOCATION = "../welcome.html" ;

    private static Map<String, Object> getMap(Object data, String msg, int success) {
        Map<String, Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
        map.put(CODE, success);
        map.put(DATA, data);
        map.put(MSG, msg);
        map.put(PIC, PIC_LOCATION);
        return map;
    }

    public static Map<String, Object> success(){
        return getMap(null, null, SUCCESS);
    }

    public static Map<String, Object> success(Object data, String msg){
        return getMap(data, msg, SUCCESS);
    }

    public static Map<String, Object> success(Object data){
        Map<String, Object> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
        map.put(CODE, SUCCESS);
        map.put(PIC, PIC_LOCATION);
        map.put(DATA, data);
        return map;
    }

    public static Map<String,Object> success(String msg){
        return getMap(null, msg, SUCCESS);
    }

    public static Map<String, Object> error(Object data){
        return getMap(data, null, ERROR);
    }

    public static Map<String, Object> error(){
        return getMap(null, null, ERROR);
    }

    public static Map<String, Object> error(Object data, String msg){
        return getMap(data, msg, ERROR);
    }

    public static Map<String, java.io.Serializable> error(String msg){
        Map<String, java.io.Serializable> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
        map.put(CODE, ERROR);
        map.put(MSG, msg);
        return map;
    }

    /**
     * 判断是否为中文
     *
     * @author zwy
     * @date 2019/3/15 17:56
     */
    public static boolean isChinese(String str) {
        String regEx = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(regEx);
        Matcher matcher = pat.matcher(str);
        return matcher.find();
    }
}
