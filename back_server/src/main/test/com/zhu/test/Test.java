package com.zhu.test;

import java.util.Arrays;

import static com.zhu.base.constant.SysConstant.Punctuation.COMMA;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2019/3/2 13:29
 *     email        1092478224@qq.com
 *     desc         测试类
 * </pre>
 */
public class Test {

    public static void main(String[] args) {
        StringBuilder willDelIds = new StringBuilder();
        StringBuilder willAddIds = new StringBuilder() ;
        StringBuilder willUpdateIds = new StringBuilder() ;
        StringBuilder wiUpdateCateIds = new StringBuilder();
        StringBuilder willContain = new StringBuilder();

        String[] orginClassIds = {"1","3"};
        String[] clsIds = {"1","2"};

        for (String orginClassId:orginClassIds){
            /*
             * 筛选出两个数组中相同的值，>= 0 表示相同，< 0 表示不同 。
             * 有相同值是返回元素的下标值。
             * 此处采用的是 "二分搜索法来搜索指定数组"。
             * */
            if(Arrays.binarySearch(clsIds,orginClassId) == 0){
                willContain.append(orginClassId).append(COMMA);

            }else if (Arrays.binarySearch(clsIds,orginClassId) < 0){
                willDelIds.append(orginClassId).append(COMMA);
            }
        }
        for (String clsId:clsIds){
            if(Arrays.binarySearch(orginClassIds,clsId) < 0){
                willAddIds.append(clsId).append(COMMA);
                willUpdateIds.append(clsId).append(COMMA);
            }
        }
        System.out.println("willDelIds:"+willDelIds);
        System.out.println("willAddIds:"+willAddIds);
        System.out.println("contain:"+willContain);
        System.out.println(orginClassIds.equals(clsIds));

//        List a = new ArrayList();
//        List b = new ArrayList();
//        b.add("a");
//        b.add("b");
//        b.add("c");
//        a.addAll(b);
//        System.out.println(a.toString());

    }
}
