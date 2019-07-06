package com.gis.measure.repository;
import com.gis.measure.dataobject.ReturnRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReturnRecordRepository extends JpaRepository<ReturnRecord, String> {
    List<ReturnRecord> findAllByIsConfirm(Integer isConfirm);
    List<ReturnRecord> findAllByStudentId(String studentId);
    ReturnRecord findByInstrumentIdAndIsConfirm(String instrumentId, Integer isConfirm);
}
