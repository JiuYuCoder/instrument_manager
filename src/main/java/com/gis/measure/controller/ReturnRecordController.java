package com.gis.measure.controller;

import com.gis.measure.dataobject.ReturnRecord;
import com.gis.measure.service.ReturnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
public class ReturnRecordController {
    @Autowired
    private ReturnRecordService returnRecordService;

    /**
     * 查询所有外借记录
     * @return
     */
    @GetMapping("/manager/return/record/find/all")
    public ModelAndView findAllRecord(@RequestParam(value = "page", defaultValue = "1")int page,
                                      @RequestParam(value = "size", defaultValue = "20")int size,
                                      Map<String, Object> map){
        Sort sort1 = new Sort("isConfirm");
        Sort sort2 = new Sort(Sort.Direction.DESC, "returnTime");
        PageRequest request = new PageRequest(page - 1, size, sort1.and(sort2));
        Page<ReturnRecord> returnRecordPage =  returnRecordService.findAll(request);
        map.put("returnRecordPage", returnRecordPage);
        map.put("size", size);
        map.put("currentPage", page);
        return new ModelAndView("returnrecord/list", map);
    }


    /**
     * 查询所有还未被确定的还记录，供管理员确认
     * @param status
     * @return
     */
    @GetMapping("/manager/return/record/find/confirm")
    public List<ReturnRecord> findNotConfirmReturnRecord(@RequestParam("confirmStatus")Integer status){
        return returnRecordService.findAllByIsConfirm(status);
    }

    /**
     * 更新记录的确认状态（未确认->已确认）
     * @param instrumentId
     * @param isConfirm
     * @return
     */
    @GetMapping("/manager/return/record/update")
    public ModelAndView confirmReturn
    (@RequestParam("instrumentId")String instrumentId, @RequestParam("isConfirm")Integer isConfirm, Map<String, Object> map){
        returnRecordService.update(instrumentId, isConfirm);
        map.put("url", "/measure/manager/return/record/find/all");
        map.put("msg", "更新成功，已批准。");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/manager/return/record/delete")
    public ModelAndView delete(@RequestParam("recordId")String recordId, Map<String, Object> map){
        returnRecordService.delete(recordId);
        map.put("url", "/measure/manager/return/record/find/all");
        map.put("msg", "更新成功，已删除。");
        return new ModelAndView("common/success", map);
    }

    /**
     * 以学号为条件查询所有还记录
     * @param studentId
     * @return
     */
    @GetMapping("/user/return/record/find/app")
    public List<ReturnRecord> findAllReturnRecordByStudentIdForApp(@RequestParam("studentId") String studentId){
        return returnRecordService.findAllByStudentId(studentId);
    }

    /**
     * 以学号为条件查询所有还记录，返回ModelAndView，供网站调用
     * @param studentId
     * @return
     */
    @GetMapping("/manager/return/record/find")
    public ModelAndView findAllReturnRecordByStudentId(@RequestParam("studentId") String studentId,
                                                       @RequestParam(value = "page", defaultValue = "1")int page,
                                                       @RequestParam(value = "size", defaultValue = "20")int size,
                                                       Map<String, Object> map){
        Page<ReturnRecord> returnRecordPage = new PageImpl<>(returnRecordService.findAllByStudentId(studentId));
        map.put("size", size);
        map.put("currentPage", page);
        map.put("returnRecordPage", returnRecordPage);
        return new ModelAndView("returnrecord/list", map);
    }
}
