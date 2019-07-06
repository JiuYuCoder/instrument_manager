package com.gis.measure.repository;

import com.gis.measure.dataobject.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, String>{
    List<BorrowRecord> findAllByIsConfirm(Integer isConfirm);
    List<BorrowRecord> findAllByStudentId(String studentId);
    BorrowRecord findByInstrumentIdAndIsConfirm(String instrumentId, Integer isConfirm);
}
