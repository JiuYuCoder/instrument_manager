package com.gis.measure.repository;

import com.gis.measure.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, String>{
}
