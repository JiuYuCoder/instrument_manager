package com.gis.measure.service.impl;

import com.gis.measure.dataobject.BorrowRecord;
import com.gis.measure.dataobject.Instrument;
import com.gis.measure.dataobject.User;
import com.gis.measure.enums.ConfirmStatusEnum;
import com.gis.measure.enums.InstrumentStatusEnum;
import com.gis.measure.enums.ResultEnum;
import com.gis.measure.exception.MyException;
import com.gis.measure.repository.BorrowRecordRepository;
import com.gis.measure.service.BorrowRecordService;
import com.gis.measure.service.InstrumentService;
import com.gis.measure.service.UserService;
import com.gis.measure.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {
    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private UserService userService;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    /**
     * 创建新的记录，Controller层只填充了studentId和instrumentId
     * @param borrowRecord
     * @return
     */
    @Override
    public BorrowRecord create(BorrowRecord borrowRecord) {
        Instrument instrument = instrumentService.findOne(borrowRecord.getInstrumentId());
        User user = userService.findOne(borrowRecord.getStudentId());

        if(instrument == null){
            throw new MyException(ResultEnum.INSTRUMENT_NOT_EXIT);
        }

        if(instrument.getIsReturn() == InstrumentStatusEnum.NOT_RETURN.getCode()){
            throw new MyException(ResultEnum.INSTRUMENT_NOT_RETURN);
        }

        if(user == null){
            throw new MyException(ResultEnum.USER_NOT_EXIT);
        }

        borrowRecord.setBorrowId(KeyUtil.genUniqueKey());
        borrowRecord.setInstrumentName(instrument.getInstrumentName());
        borrowRecord.setStudentName(user.getStudentName());

        return borrowRecordRepository.save(borrowRecord);
    }

    /**
     * 管理员确认后调用，更新确认状态、和确认时间
     * @param instrumentId
     * @param isConfirm
     * @return
     */
    @Override
    public BorrowRecord update(String instrumentId, Integer isConfirm) {
        BorrowRecord result = borrowRecordRepository.
                findByInstrumentIdAndIsConfirm(instrumentId, isConfirm);

        //更新仪器的归还状态
        Instrument instrument = instrumentService.findOne(instrumentId);
        instrument.setIsReturn(InstrumentStatusEnum.NOT_RETURN.getCode());
        instrumentService.update(instrument);

        result.setIsConfirm(ConfirmStatusEnum.CONFIRMED.getCode());

        return borrowRecordRepository.save(result);
    }

    @Override
    public void delete(String recordId) {
        borrowRecordRepository.delete(recordId);
    }

    /**
     * 找出所有未确定的借记录，供管理员确认
     * @param isConfirm
     * @return
     */
    @Override
    public List<BorrowRecord> findAllByIsConfirm(Integer isConfirm) {
        return borrowRecordRepository.findAllByIsConfirm(isConfirm);
    }

    /**
     * 找出某一用户所有的借记录
     * @param studentId
     * @return
     */
    @Override
    public List<BorrowRecord> findAllByStudentId(String studentId) {
        return borrowRecordRepository.findAllByStudentId(studentId);
    }

    /**
     * 查找所有借记录
     * @return
     */
    @Override
    public Page<BorrowRecord> findAll(Pageable pageable) {
        return borrowRecordRepository.findAll(pageable);
    }
}
