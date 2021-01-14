package com.zhu.base.exception;

/**
 * 系统自定义异常
 *
 * @author zwy
 * @date 2018/11/28 14:06
 */
public class TjoaException extends Exception {

    //异常信息
    private String message ;

    private String code ;

    public TjoaException(String message){
        super(message);
        this.message = message ;
    }

    public TjoaException(String message,String code){
        super(message);
        this.message = message ;
        this.code = code ;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
