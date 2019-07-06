package com.gis.measure.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    USER_NOT_EXIT(10, "该用户不存在"),
    INSTRUMENT_NOT_EXIT(11, "该仪器不存在"),
    GROUP_ERROR(12, "组号码非法"),
    INSTRUMENT_CODE_ERROR(13, "仪器码错误"),
    INSTRUMENT_NAME_ERROR(14, "仪器名称错误"),
    INSTRUMENT_IS_EXIT(15, "该仪器已存在"),
    INSTRUMENT_NOT_RETURN(16, "仪器未归还"),
    INSTRUMENT_RETURNED(17, "该仪器已归还"),
    MANAGER_NOT_EXIT(18, "你还不是管理员"),
    PASSWORD_ERROR(19, "密码错误"),

    APP_LOGIN_ERROR(90, "登陆失败"),
    APP_LOGIN_SUCCESS(91, "登陆成功"),


    ;


    private int code;
    private String message;
    ResultEnum(int code, String msg){
        this.code = code;
        this.message = msg;
    }
}
