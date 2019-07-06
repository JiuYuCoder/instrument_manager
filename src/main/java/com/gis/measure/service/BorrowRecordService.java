package com.gis.measure.service;

import com.gis.measure.dataobject.BorrowRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BorrowRecordService {
    BorrowRecord create(BorrowRecord borrowRecord);
    BorrowRecord update(String instrumentId, Integer isConfirm);
    void delete(String recordId);
    List<BorrowRecord> findAllByIsConfirm(Integer isConfirm);
    List<BorrowRecord> findAllByStudentId(String studentId);
    Page<BorrowRecord> findAll(Pageable pageable);
}
