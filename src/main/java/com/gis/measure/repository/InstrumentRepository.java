package com.gis.measure.repository;

import com.gis.measure.dataobject.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstrumentRepository extends JpaRepository<Instrument, String> {
    List<Instrument> findAllByInstrumentName(String instrumentName);

}
