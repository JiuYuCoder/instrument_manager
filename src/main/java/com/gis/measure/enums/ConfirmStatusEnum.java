package com.gis.measure.enums;

import lombok.Getter;

@Getter
public enum ConfirmStatusEnum {
    NOT_CONFIRM(0, "管理员未确认"),
    CONFIRMED(1, "管理员已确认"),
    ;

    private int code;
    private String message;
    ConfirmStatusEnum(int code, String msg){
        this.code = code;
        this.message = msg;
    }
}
