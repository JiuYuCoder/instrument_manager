package com.gis.measure.controller;

import com.gis.measure.constant.CookieConstant;
import com.gis.measure.dataobject.Manager;
import com.gis.measure.dataobject.User;
import com.gis.measure.enums.ResultEnum;
import com.gis.measure.exception.MyException;
import com.gis.measure.form.LoginForm;
import com.gis.measure.service.ManagerService;
import com.gis.measure.service.UserService;
import com.gis.measure.utils.CookieUtil;
import com.gis.measure.vo.AppResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private ManagerService managerService;

    /**
     * 后台管理员登陆
     * @param form
     * @param bindingResult
     * @param response
     * @param map
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(@Valid LoginForm form, BindingResult bindingResult,
                              HttpServletResponse response, Map<String, Object> map){
        if(bindingResult.hasErrors()){
            throw new MyException(bindingResult.getFieldError().getDefaultMessage());
        }

        //1.匹配账号密码
        Manager manager = managerService.findOne(form.getStudentId());
        User user = userService.findOne(manager.getStudentId());
        if(!user.getPassword().contentEquals(form.getPassword())){
            map.put("msg", ResultEnum.PASSWORD_ERROR.getMessage());
            map.put("url", "/measure/getLogin");
            return new ModelAndView("common/error");
        }
        //2.设置token至cookie
        String token = UUID.randomUUID().toString();
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);

        return new ModelAndView("redirect:http://127.0.0.1:8080/measure/manager/user/find/all");
    }

    @GetMapping("/getLogin")
    public ModelAndView login(){
        return new ModelAndView("common/login");
    }

    @ResponseBody
    @PostMapping("/app/login")
    public AppResultVO appLogin(@Valid LoginForm form, BindingResult bindingResult){
        //如果表单验证异常
        if(bindingResult.hasErrors()){
            return new AppResultVO(ResultEnum.APP_LOGIN_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        //表单正常就进行账号密码匹配
        User user = userService.findOneStudent(form.getStudentId());
        //检验用户是否存在
        if(user == null){
            return new AppResultVO(ResultEnum.USER_NOT_EXIT);
        }
        //检验密码
        if(!user.getPassword().contentEquals(form.getPassword())){
            return new AppResultVO(ResultEnum.PASSWORD_ERROR);
        }
        //登陆成功
        return new AppResultVO(ResultEnum.APP_LOGIN_SUCCESS);
    }
}
