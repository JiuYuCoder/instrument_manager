package com.gis.measure.controller;

import com.gis.measure.dataobject.BorrowRecord;
import com.gis.measure.service.BorrowRecordService;
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
public class BorrowRecordController {
    @Autowired
    private BorrowRecordService borrowRecordService;

    /**
     * 查询所有外借记录
     * @return
     */
    @GetMapping("/manager/borrow/record/find/all")
    public ModelAndView findAllRecord(@RequestParam(value = "page", defaultValue = "1")int page,
                                      @RequestParam(value = "size", defaultValue = "20")int size,
                                      Map<String, Object> map){
        Sort sort1 = new Sort("isConfirm");
        Sort sort2 = new Sort(Sort.Direction.DESC, "borrowTime");
        PageRequest request = new PageRequest(page - 1, size, sort1.and(sort2));
        Page<BorrowRecord> borrowRecordPage =  borrowRecordService.findAll(request);
        map.put("borrowRecordPage", borrowRecordPage);
        map.put("size", size);
        map.put("currentPage", page);
        return new ModelAndView("borrowrecord/list", map);
    }


    /**
     * 查询所有还未被确定的借记录，供管理员确认
     * @param status
     * @return
     */
    @GetMapping("/manager/borrow/record/find/confirm")
    public List<BorrowRecord> findNotConfirmBorrowRecord(@RequestParam("confirmStatus")Integer status){
        return borrowRecordService.findAllByIsConfirm(status);
    }


    /**
     * 更新记录的确认状态（未确认->已确认）
     * @param instrumentId
     * @param isConfirm
     * @return
     */
    @GetMapping("/manager/borrow/record/update")
    public ModelAndView confirmBorrow
    (@RequestParam("instrumentId")String instrumentId, @RequestParam("isConfirm")Integer isConfirm, Map<String, Object> map){
        borrowRecordService.update(instrumentId, isConfirm);
        map.put("url", "/measure/manager/borrow/record/find/all");
        map.put("msg", "更新成功，已批准。");
        return new ModelAndView("common/success", map);
    }


    @GetMapping("/manager/borrow/record/delete")
    public ModelAndView delete(@RequestParam("recordId")String recordId, Map<String, Object> map){
        borrowRecordService.delete(recordId);
        map.put("url", "/measure/manager/borrow/record/find/all");
        map.put("msg", "更新成功，已删除。");
        return new ModelAndView("common/success", map);
    }


    /**
     * 以学号为条件查询所有借记录，返回JSON，供APP调用展示
     * @param studentId
     * @return
     */
    @GetMapping("/user/borrow/record/find/app")
    public List<BorrowRecord> findAllBorrowRecordByStudentIdForApp(@RequestParam("studentId") String studentId){
        return borrowRecordService.findAllByStudentId(studentId);
    }


    /**
     * 以学号为条件查询所有借记录，返回ModelAndView，供网站调用
     * @param studentId
     * @return
     */
    @GetMapping("/manager/borrow/record/find")
    public ModelAndView findAllBorrowRecordByStudentId(@RequestParam("studentId") String studentId,
                                                       @RequestParam(value = "page", defaultValue = "1")int page,
                                                       @RequestParam(value = "size", defaultValue = "20")int size,
                                                             Map<String, Object> map){
        Page<BorrowRecord> borrowRecordPage = new PageImpl<>(borrowRecordService.findAllByStudentId(studentId));
        map.put("size", size);
        map.put("currentPage", page);
        map.put("borrowRecordPage", borrowRecordPage);
        return new ModelAndView("borrowrecord/list", map);
    }
}
