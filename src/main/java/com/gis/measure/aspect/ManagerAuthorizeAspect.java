package com.gis.measure.aspect;

import com.gis.measure.constant.CookieConstant;
import com.gis.measure.exception.LoginException;
import com.gis.measure.exception.MyException;
import com.gis.measure.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class ManagerAuthorizeAspect {

    @Pointcut("execution(public * com.gis.measure.controller.*.*(..))" +
    "&& !execution(public * com.gis.measure.controller.LoginController.*(..))" +
    "&& !execution(public * com.gis.measure.controller.ReturnRecordController.findAllReturnRecordByStudentIdForApp(..))" +
    "&& !execution(public * com.gis.measure.controller.BorrowRecordController.findAllBorrowRecordByStudentIdForApp(..))" +
    "&& !execution(public * com.gis.measure.controller.InstrumentController.borrowInstrument(..))" +
    "&& !execution(public * com.gis.measure.controller.InstrumentController.returnInstrument(..))" +
    "&& !execution(public * com.gis.measure.controller.UserController.updateGroup(..))" +
    "&& !execution(public * com.gis.measure.controller.UserController.updatePassword(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询Cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie == null){
            log.error("【登陆校验】Cookie不存在Token");
            throw new LoginException();
        }
    }
}
