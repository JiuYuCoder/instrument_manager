package com.gis.measure.service;

import com.gis.measure.dataobject.ReturnRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReturnRecordService {
    ReturnRecord create(ReturnRecord borrowRecord);
    ReturnRecord update(String instrumentId, Integer isConfirm);
    void delete(String recordId);
    Page<ReturnRecord> findAll(Pageable pageable);
    List<ReturnRecord> findAllByIsConfirm(Integer isConfirm);
    List<ReturnRecord> findAllByStudentId(String studentId);
}
