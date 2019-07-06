package com.gis.measure.vo;

import com.gis.measure.enums.ResultEnum;
import lombok.Data;

@Data
public class AppResultVO {
    private int code;
    private String msg;
    public AppResultVO(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public AppResultVO(ResultEnum resultEnum){
        code = resultEnum.getCode();
        msg = resultEnum.getMessage();
    }
}
