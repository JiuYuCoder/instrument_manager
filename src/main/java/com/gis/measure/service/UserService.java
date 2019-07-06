package com.gis.measure.service;

import com.gis.measure.dataobject.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User findOne(String studentId);

    User findOneStudent(String studentId);

    Page<User> findAll(Pageable pageable);

    User create(User user);

    void delete(String studentId);

    User updateGroup(String studentId, Integer group);

    User updatePassword(String studentId, String password);
}
