package com.gis.measure.handler;

import com.gis.measure.exception.LoginException;
import com.gis.measure.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ModelAndView handleMyException(MyException e){
        Map<String, Object> map = new HashMap<>();
        map.put("url", "/measure/manager/instrument/find/all");
        map.put("msg", e.getMessage());
        return new ModelAndView("common/error", map);
    }

    //拦截登录异常
    @ExceptionHandler(value = LoginException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("common/login");
    }
}
