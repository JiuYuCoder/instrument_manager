package com.gis.measure.controller;

import com.gis.measure.dataobject.BorrowRecord;
import com.gis.measure.dataobject.Instrument;
import com.gis.measure.dataobject.ReturnRecord;
import com.gis.measure.exception.MyException;
import com.gis.measure.form.BorrowAndReturnForm;
import com.gis.measure.form.InstrumentForm;
import com.gis.measure.service.BorrowRecordService;
import com.gis.measure.service.InstrumentService;
import com.gis.measure.service.ReturnRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class InstrumentController {
    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private BorrowRecordService borrowRecordService;

    @Autowired
    private ReturnRecordService returnRecordService;

    /**
     * 使用仪器码查询仪器
     * @param instrumentId
     * @return
     */
    @GetMapping("/manager/instrument/find/one")
    public ModelAndView findOneInstrument(@RequestParam("instrumentId")String instrumentId, Map<String, Object> map){
        List<Instrument> instrumentList = new ArrayList<>();
        instrumentList.add(instrumentService.findOne(instrumentId));
        Page<Instrument> instrumentPage = new PageImpl<>(instrumentList);
        map.put("instrumentPage", instrumentPage);
        map.put("currentPage", 1);
        map.put("size", 10);
        return new ModelAndView("instrument/list", map);
    }

    /**
     * 使用名称查询仪器
     * @param instrumentName
     * @return
     */
    @GetMapping("/manager/instrument/find/name")
    public ModelAndView findAllInstrumentByName(@RequestParam("instrumentName")String instrumentName,
                                                Map<String, Object> map){
        PageRequest request = new PageRequest(0, 20);
        List<Instrument> instrumentList =  instrumentService.findAllByInstrumentName(instrumentName);
        Page<Instrument> instrumentPage = new PageImpl<>(instrumentList, request, instrumentList.size());
        map.put("currentPage", 1);
        map.put("size", 20);
        map.put("instrumentPage", instrumentPage);
        return new ModelAndView("instrument/list", map);
    }

    /**
     * 查询所有仪器
     * @return
     */
    @GetMapping("/manager/instrument/find/all")
    public ModelAndView findAllInstrument(@RequestParam(value = "page", defaultValue = "1") int page,
                                          @RequestParam(value = "size", defaultValue = "20")int size,
                                          Map<String, Object> map){
        PageRequest request = new PageRequest(page - 1, size);
        Page<Instrument> instrumentPage = instrumentService.findAll(request);
        map.put("instrumentPage", instrumentPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("instrument/list", map);
    }


    /**
     * 新建/修改
     * @param instrumentId
     * @param map
     * @return
     */
    @GetMapping("/manager/instrument/index")
    public ModelAndView instrumentIndex(@RequestParam(value = "instrumentId", required = false)String instrumentId,
                                        Map<String, Object> map){
        if(!StringUtils.isEmpty(instrumentId)){
            Instrument instrument = instrumentService.findOne(instrumentId);
            map.put("instrument", instrument);
        }

        return new ModelAndView("instrument/index", map);
    }

    /**
     * 创建新仪器
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/manager/instrument/save")
    public ModelAndView createInstrument(@Valid InstrumentForm form, BindingResult bindingResult, Map<String, Object> map){
        map.put("url", "/measure/manager/instrument/find/all");
        if(bindingResult.hasErrors()){
            map.put("msg", "保存失败：" + bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/error", map);
        }
        Instrument instrument = new Instrument();
        BeanUtils.copyProperties(form, instrument);
        instrumentService.create(instrument);
        map.put("msg", "保存成功");
        return new ModelAndView("common/success", map);
    }

    /**
     * 删除对应仪器
     * @param instrumentId
     */
    @GetMapping("/manager/instrument/delete")
    public ModelAndView deleteInstrument(@RequestParam("instrumentId")String  instrumentId, Map<String, Object> map){
        map.put("url", "/measure/manager/instrument/find/all");
        try {
            instrumentService.delete(instrumentId);
        }catch (MyException e){
            map.put("msg", "删除失败：" + e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "删除成功");
        return new ModelAndView("common/success", map);
    }


    /**
     * 删除统一名称的所有仪器
     * @param instrumentName
     */
    @GetMapping("/manager/instrument/delete/name")
    public ModelAndView deleteInstrumentByName(@RequestParam("instrumentName")String instrumentName, Map<String, Object> map){
        map.put("url", "/measure/manager/instrument/find/all");
        try {
            instrumentService.delete(instrumentName);
        }catch (MyException e){
            map.put("msg", "删除失败：" + e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "删除成功");
        return new ModelAndView("common/success", map);
    }

    /**
     * 借仪器，检验后插入借记录表
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/user/borrow")
    public BorrowRecord borrowInstrument(@Valid BorrowAndReturnForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MyException(bindingResult.getFieldError().getDefaultMessage());
        }

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setInstrumentId(form.getInstrumentId());
        borrowRecord.setStudentId(form.getStudentId());

        return borrowRecordService.create(borrowRecord);
    }

    /**
     * 还仪器
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/user/return")
    public ReturnRecord returnInstrument(@Valid BorrowAndReturnForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MyException(bindingResult.getFieldError().getDefaultMessage());
        }

        ReturnRecord returnRecord = new ReturnRecord();
        returnRecord.setInstrumentId(form.getInstrumentId());
        returnRecord.setStudentId(form.getStudentId());
        return returnRecordService.create(returnRecord);
    }
}
