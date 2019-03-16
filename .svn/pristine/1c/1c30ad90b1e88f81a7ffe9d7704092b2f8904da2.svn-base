package com.zrtjoa.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.zrtjoa.constant.SysConstant.MAP_DEFAULT_SIZE;
import static com.zrtjoa.constant.SysConstant.Punctuation.COMMA;
import static com.zrtjoa.constant.SysConstant.Punctuation.SLASH;

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

        Arrays.asList(classIds.split(COMMA)).forEach(clsId->{
            cateIds.append(clsId.split(SLASH)[0]).append(COMMA);
            majorIds.append(clsId.split(SLASH)[1]).append(COMMA);
            clsIds.append(clsId.split(SLASH)[2]).append(COMMA);
        });
        Arrays.asList(classNames.split(COMMA)).forEach(clsName->{
            cateNames.append(clsName.split(SLASH)[0]).append(COMMA);
            majorNames.append(clsName.split(SLASH)[1]).append(COMMA);
            clsNames.append(clsName.split(SLASH)[2]).append(COMMA);
        });

        Map<String,String> map = new HashMap<>(MAP_DEFAULT_SIZE);
        map.put("cateIds",cateIds.substring(0,cateIds.lastIndexOf(COMMA)));
        map.put("majorIds",majorIds.substring(0,majorIds.lastIndexOf(COMMA)));
        map.put("clsIds",clsIds.substring(0,clsIds.lastIndexOf(COMMA)));
        map.put("cateNames",cateNames.substring(0,cateNames.lastIndexOf(COMMA)));
        map.put("majorNames",majorNames.substring(0,majorNames.lastIndexOf(COMMA)));
        map.put("clsNames",clsNames.substring(0,clsNames.lastIndexOf(COMMA)));

        return map;
    }
}
