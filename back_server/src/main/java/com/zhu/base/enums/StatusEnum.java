package com.zhu.base.enums;

/**
 * StatusEnum enum
 * 状态类的枚举
 *
 * @author zwy
 * @date 2018/11/30 14:01
 */
public enum StatusEnum {

    /**
     * 状态是否可用
     *
     * @date 2018/11/30 14:09
     */
    THIS_TERM("0","当前学期"),
    ACTIVE_TERM("1","已激活"),
    DISABLE_TERM("2","未激活"),
    PASS_DATE_TERM("3","过期")
    ;

    public final String code ;

    public final String msg ;

    StatusEnum(String code,String msg){
        this.code = code ;
        this.msg = msg ;
    }

    public static StatusEnum returnEnumByCode(String code){
        for (StatusEnum statusEnum: values()) {
            if (statusEnum.code.equals(code)){
                return statusEnum ;
            }
        }
        throw new IllegalArgumentException("there is no code [" + code + "] for enum");
    }
}
