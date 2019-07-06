package com.gis.measure.controller;

import com.gis.measure.dataobject.*;
import com.gis.measure.exception.MyException;
import com.gis.measure.form.InstrumentForm;
import com.gis.measure.form.ManagerForm;
import com.gis.measure.form.UserForm;
import com.gis.measure.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manager")
@Slf4j
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    /**
     * 查询对应的管理员
     * @param studentId
     * @return
     */
    @GetMapping("/manager/find/one")
    public ModelAndView findOneManager(@RequestParam("studentId") String studentId, Map<String, Object> map){
        List<Manager> managerList = new ArrayList<>();
        managerList.add(managerService.findOne(studentId));
        Page<Manager> managerPage = new PageImpl<>(managerList);
        map.put("managerPage", managerPage);
        map.put("currentPage", 1);
        map.put("size", 10);
        return new ModelAndView("manager/list", map);
    }

    /**
     * 查询所有管理员
     * @return
     */
    @GetMapping("/manager/find/all")
    public ModelAndView findAllManager(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "size", defaultValue = "20")int size,
                                       Map<String, Object> map){
        PageRequest request = new PageRequest(page - 1, size);
        Page<Manager> managerPage =  managerService.findAll(request);
        map.put("managerPage", managerPage);
        map.put("currentPage", page);
        map.put("size", size);
        log.error(map.toString());
        return new ModelAndView("manager/list", map);
    }

    /**
     * 创建/修改管理员
     * @param studentId
     * @param map
     * @return
     */
    @GetMapping("/manager/index")
    public ModelAndView managerIndex(@RequestParam(value = "studentId", required = false)String studentId,
                                        Map<String, Object> map){
        if(!StringUtils.isEmpty(studentId)){
            Manager manager = managerService.findOne(studentId);
            map.put("manager", manager);
        }

        return new ModelAndView("manager/index", map);
    }

    /**
     *  创建新管理员
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/manager/save")
    public ModelAndView createManager(@Valid ManagerForm form, BindingResult bindingResult, Map<String, Object> map){
        map.put("url", "/measure/manager/manager/find/all");
        if(bindingResult.hasErrors()){
            map.put("msg", "保存失败：" + bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/error", map);
        }
        Manager manager = new Manager();
        BeanUtils.copyProperties(form, manager);
        managerService.create(manager);
        map.put("msg", "保存成功");
        return new ModelAndView("common/success", map);
    }

    /**
     * 删除对应管理员
     * @param studentId
     */
    @GetMapping("/manager/delete")
    public ModelAndView deleteManager(@RequestParam("studentId") String studentId, Map<String, Object> map){
        map.put("url", "/measure/manager/manager/find/all");
        try {
            managerService.delete(studentId);
        }catch (MyException e){
            map.put("msg", "删除失败：" + e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "删除成功");
        return new ModelAndView("common/success", map);
    }
}
