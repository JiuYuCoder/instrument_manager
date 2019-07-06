package com.gis.measure.exception;

import com.gis.measure.enums.ResultEnum;

public class MyException extends RuntimeException {
    private int code;

    public MyException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public MyException(int code, String msg){
        super(msg);
        this.code = code;
    }

    public MyException(String msg){
        super(msg);
    }
}
