package com.gis.measure.service;

import com.gis.measure.dataobject.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ManagerService {
    Manager findOne(String studentId);
    Page<Manager> findAll(Pageable pageable);
    Manager create(Manager manager);
    void delete(String studentId);
}
