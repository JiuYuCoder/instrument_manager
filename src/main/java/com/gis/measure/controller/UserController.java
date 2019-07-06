package com.gis.measure.controller;

import com.gis.measure.dataobject.User;
import com.gis.measure.exception.MyException;
import com.gis.measure.form.*;
import com.gis.measure.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 更新用户的组号
     * @param from
     * @param bindingResult
     */
    @PostMapping("/user/update/group")
    public User updateGroup(@Valid GroupFrom from, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MyException(bindingResult.getFieldError().getDefaultMessage());
        }
        return userService.updateGroup(from.getStudentId(), from.getStudentGroup());
    }

    /**
     * 更新用户的密码
     * @param form
     * @param bindingResult
     */
    @PostMapping("/user/update/password")
    public User updatePassword(@Valid PasswordForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MyException(bindingResult.getFieldError().getDefaultMessage());
        }
        return userService.updatePassword(form.getStudentId(), form.getPassword());
    }


    /**
     * 查询单个用户
     * @param studentId
     * @return
     */
    @GetMapping("/manager/user/find/one")
    public ModelAndView findOneUser(@RequestParam("studentId") String studentId, Map<String, Object> map){
        List<User> userList = new ArrayList<>();
        userList.add(userService.findOne(studentId));
        Page<User> userPage = new PageImpl<>(userList);
        map.put("userPage", userPage);
        map.put("currentPage", 1);
        map.put("size", 10);
        return new ModelAndView("user/list", map);
    }

    /**
     * 查询所有用户
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/manager/user/find/all")
    public ModelAndView findAllUser(@RequestParam(value = "page", defaultValue = "1")int page,
                                    @RequestParam(value = "size", defaultValue = "20")int size,
                                    Map<String, Object> map){
        PageRequest request = new PageRequest(page - 1, size);
        Page<User> userPage = userService.findAll(request);
        map.put("userPage", userPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("user/list", map);
    }

    /**
     * 新建/修改
     * @param studentId
     * @param map
     * @return
     */
    @GetMapping("/manager/user/index")
    public ModelAndView userIndex(@RequestParam(value = "studentId", required = false)String studentId,
                                  Map<String, Object> map){
        if(!StringUtils.isEmpty(studentId)){
            User user = userService.findOne(studentId);
            map.put("user", user);
        }

        return new ModelAndView("user/index", map);
    }

    /**
     * 创建、修改新用户
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/manager/user/save")
    public ModelAndView saveUser(@Valid UserForm form, BindingResult bindingResult, Map<String, Object> map){
        map.put("url", "/measure/manager/user/find/all");
        if(bindingResult.hasErrors()){
            map.put("msg", "保存失败：" + bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/error", map);
        }
        User user = new User();
        BeanUtils.copyProperties(form, user);
        userService.create(user);
        map.put("msg", "保存成功");
        return new ModelAndView("common/success", map);
    }

    /**
     * 删除用户
     * @param studentId
     */
    @GetMapping("/manager/user/delete")
    public ModelAndView deleteUser(@RequestParam("studentId")String studentId, Map<String, Object> map){
        map.put("url", "/measure/manager/user/find/all");
        try {
            userService.delete(studentId);
        }catch (MyException e){
            map.put("msg", "删除失败：" + e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "删除成功");
        return new ModelAndView("common/success", map);
    }
}
