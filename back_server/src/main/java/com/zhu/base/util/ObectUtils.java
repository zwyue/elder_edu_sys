package com.zhu.base.util;

import com.zhu.base.constant.SysConstant;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2019/2/19 17:02
 *     email        1092478224@qq.com
 *     desc         对象工具类
 * </pre>
 */
public class ObectUtils {

    /**
     *  java反射机制判断对象属性是否不为空
     * @param obj
     * @return 返回属性名称
     */
    public static boolean checkObjFieldIsNotNull(Object obj){
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);

                if("serialVersionUID".equals(f.getName())){
                    continue;
                }

                if (f.get(obj) != null ) {
                    return true;
                }
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    /**
     * 将形如 1/1/23，3/4/4 的分类专业班级重新组装
     *
     * @author zwy
     * @date 2019/2/28 15:21
     */
    public static Map assembleCtgrPrfsCls(String classIds, String classNames) {

        StringBuilder cateIds = new StringBuilder();
        StringBuilder cateNames = new StringBuilder();
        StringBuilder majorIds = new StringBuilder();
        StringBuilder majorNames = new StringBuilder();
        StringBuilder clsIds = new StringBuilder();
        StringBuilder clsNames = new StringBuilder();

        Arrays.asList(classIds.split(SysConstant.Punctuation.COMMA)).forEach(clsId->{
            cateIds.append(clsId.split(SysConstant.Punctuation.SLASH)[0]).append(SysConstant.Punctuation.COMMA);
            majorIds.append(clsId.split(SysConstant.Punctuation.SLASH)[1]).append(SysConstant.Punctuation.COMMA);
            clsIds.append(clsId.split(SysConstant.Punctuation.SLASH)[2]).append(SysConstant.Punctuation.COMMA);
        });
        Arrays.asList(classNames.split(SysConstant.Punctuation.COMMA)).forEach(clsName->{
            cateNames.append(clsName.split(SysConstant.Punctuation.SLASH)[0]).append(SysConstant.Punctuation.COMMA);
            majorNames.append(clsName.split(SysConstant.Punctuation.SLASH)[1]).append(SysConstant.Punctuation.COMMA);
            clsNames.append(clsName.split(SysConstant.Punctuation.SLASH)[2]).append(SysConstant.Punctuation.COMMA);
        });

        Map<String,String> map = new HashMap<>(SysConstant.MAP_DEFAULT_SIZE);
        map.put("cateIds",cateIds.substring(0,cateIds.lastIndexOf(SysConstant.Punctuation.COMMA)));
        map.put("majorIds",majorIds.substring(0,majorIds.lastIndexOf(SysConstant.Punctuation.COMMA)));
        map.put("clsIds",clsIds.substring(0,clsIds.lastIndexOf(SysConstant.Punctuation.COMMA)));
        map.put("cateNames",cateNames.substring(0,cateNames.lastIndexOf(SysConstant.Punctuation.COMMA)));
        map.put("majorNames",majorNames.substring(0,majorNames.lastIndexOf(SysConstant.Punctuation.COMMA)));
        map.put("clsNames",clsNames.substring(0,clsNames.lastIndexOf(SysConstant.Punctuation.COMMA)));

        return map;
    }
}
