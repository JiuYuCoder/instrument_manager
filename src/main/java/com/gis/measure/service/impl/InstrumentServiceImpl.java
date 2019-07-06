package com.gis.measure.service.impl;

import com.gis.measure.dataobject.Instrument;
import com.gis.measure.enums.ConfirmStatusEnum;
import com.gis.measure.enums.InstrumentStatusEnum;
import com.gis.measure.enums.ResultEnum;
import com.gis.measure.exception.MyException;
import com.gis.measure.repository.InstrumentRepository;
import com.gis.measure.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    @Autowired
    InstrumentRepository instrumentRepository;

    /**
     * 用仪器码寻找仪器并返回
     * @param instrumentId
     * @return
     */
    @Override
    public Instrument findOne(String instrumentId) {
        if(StringUtils.isEmpty(instrumentId)){
            throw new MyException(ResultEnum.INSTRUMENT_CODE_ERROR);
        }

        Instrument instrument = instrumentRepository.findOne(instrumentId);
        if(instrument == null){
            throw new MyException(ResultEnum.INSTRUMENT_NOT_EXIT);
        }
        return instrument;
    }

    /**
     * 查询所有为指定名称的仪器
     * @param instrumentName
     * @return
     */
    @Override
    public List<Instrument> findAllByInstrumentName(String instrumentName) {
        if(StringUtils.isEmpty(instrumentName)){
            throw new MyException(ResultEnum.INSTRUMENT_NAME_ERROR);
        }
        return instrumentRepository.findAllByInstrumentName(instrumentName);
    }


    /**
     * 查询所有仪器并返回
     * @return
     */
    @Override
    public Page<Instrument> findAll(Pageable pageable) {
        return instrumentRepository.findAll(pageable);
    }

    /**
     * 删除指定的仪器
     * @param instrumentId
     */
    @Override
    public void delete(String instrumentId) {
        findOne(instrumentId);//检查仪器是否存在
        instrumentRepository.delete(instrumentId);
    }

    /**
     * 删除同名称的所有仪器
     * @param instrumentName
     */
    @Override
    public void deleteAllByName(String instrumentName) {
        if(StringUtils.isEmpty(instrumentName)){
            throw new MyException(ResultEnum.INSTRUMENT_NAME_ERROR);
        }
        List<Instrument> instrumentList = instrumentRepository.findAllByInstrumentName(instrumentName);
        for(Instrument instrument : instrumentList){
            delete(instrument.getInstrumentId());
        }
    }

    /**
     * 创建新的仪器
     * @param instrument
     * @return
     */
    @Override
    public Instrument create(Instrument instrument) {
        if(instrumentRepository.findOne(instrument.getInstrumentId()) == null){
            return instrumentRepository.save(instrument);
        }else{
            throw new MyException(ResultEnum.INSTRUMENT_IS_EXIT);
        }
    }

    @Override
    public Instrument update(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }
}
