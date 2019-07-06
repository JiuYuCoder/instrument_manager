package com.gis.measure.repository;

import com.gis.measure.dataobject.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, String>{
}
