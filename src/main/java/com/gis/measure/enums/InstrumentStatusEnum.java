package com.gis.measure.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum InstrumentStatusEnum {
    NOT_RETURN(0, "未归还"),
    RETURNED(1, "已归还"),
    ;

    private int code;
    private String message;
    InstrumentStatusEnum(int code, String msg){
        this.code = code;
        this.message = msg;
    }
}
