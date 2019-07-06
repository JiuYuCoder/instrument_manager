package com.gis.measure.service;

import com.gis.measure.dataobject.Instrument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InstrumentService {
    Instrument findOne(String instrumentId);
    List<Instrument> findAllByInstrumentName(String instrumentName);
    Page<Instrument> findAll(Pageable pageable);
    void delete(String instrumentId);
    void deleteAllByName(String instrumentName);
    Instrument create(Instrument instrument);
    Instrument update(Instrument instrument);
}
