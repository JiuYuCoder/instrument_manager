package com.gis.measure.service.impl;

import com.gis.measure.dataobject.BorrowRecord;
import com.gis.measure.dataobject.Instrument;
import com.gis.measure.dataobject.ReturnRecord;
import com.gis.measure.dataobject.User;
import com.gis.measure.enums.ConfirmStatusEnum;
import com.gis.measure.enums.InstrumentStatusEnum;
import com.gis.measure.enums.ResultEnum;
import com.gis.measure.exception.MyException;
import com.gis.measure.repository.BorrowRecordRepository;
import com.gis.measure.repository.ReturnRecordRepository;
import com.gis.measure.service.InstrumentService;
import com.gis.measure.service.ReturnRecordService;
import com.gis.measure.service.UserService;
import com.gis.measure.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnRecordServiceImpl implements ReturnRecordService{
    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReturnRecordRepository returnRecordRepository;

    /**
     * 创建新的还记录
     * @param returnRecord
     * @return
     */
    @Override
    public ReturnRecord create(ReturnRecord returnRecord) {
        Instrument instrument = instrumentService.findOne(returnRecord.getInstrumentId());
        User user = userService.findOne(returnRecord.getStudentId());

        if(instrument == null){
            throw new MyException(ResultEnum.INSTRUMENT_NOT_EXIT);
        }

        //如果仪器已经归还了，那就不能再次归还
        if(instrument.getIsReturn() == InstrumentStatusEnum.RETURNED.getCode()){
            throw new MyException(ResultEnum.INSTRUMENT_RETURNED);
        }

        if(user == null){
            throw new MyException(ResultEnum.USER_NOT_EXIT);
        }

        returnRecord.setReturnId(KeyUtil.genUniqueKey());
        returnRecord.setInstrumentName(instrument.getInstrumentName());
        returnRecord.setStudentName(user.getStudentName());

        return returnRecordRepository.save(returnRecord);
    }

    /**
     * 管理员确认后调用，更新确认状态、仪器归还状态和确认时间
     * @param instrumentId
     * @param isConfirm
     * @return
     */
    @Override
    public ReturnRecord update(String instrumentId, Integer isConfirm) {
        ReturnRecord result = returnRecordRepository.
                findByInstrumentIdAndIsConfirm(instrumentId, isConfirm);
        result.setIsConfirm(ConfirmStatusEnum.CONFIRMED.getCode());

        //更新仪器的归还状态
        Instrument instrument = instrumentService.findOne(instrumentId);
        instrument.setIsReturn(InstrumentStatusEnum.RETURNED.getCode());
        instrumentService.update(instrument);

        return returnRecordRepository.save(result);
    }

    @Override
    public void delete(String recordId) {
        returnRecordRepository.delete(recordId);
    }

    @Override
    public Page<ReturnRecord> findAll(Pageable pageable) {
        return returnRecordRepository.findAll(pageable);
    }

    /**
     * 找出所有未确定的还记录，供管理员确认
     * @param isConfirm
     * @return
     */
    @Override
    public List<ReturnRecord> findAllByIsConfirm(Integer isConfirm) {
        return returnRecordRepository.findAllByIsConfirm(isConfirm);
    }

    /**
     * 找出某一用户所有的还记录
     * @param studentId
     * @return
     */
    @Override
    public List<ReturnRecord> findAllByStudentId(String studentId) {
        return returnRecordRepository.findAllByStudentId(studentId);
    }
}
