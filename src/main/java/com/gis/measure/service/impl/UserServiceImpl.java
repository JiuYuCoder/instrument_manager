package com.gis.measure.service.impl;

import com.gis.measure.dataobject.User;
import com.gis.measure.enums.ResultEnum;
import com.gis.measure.exception.MyException;
import com.gis.measure.repository.UserRepository;
import com.gis.measure.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 用学号寻找学生并返回
     * @param studentId
     * @return
     */
    @Override
    public User findOne(String studentId) {
        if(studentId == null){
            throw new MyException("学号为空");
        }

        User user = userRepository.findOne(studentId);
        if(user == null){
            throw new MyException(ResultEnum.USER_NOT_EXIT);
        }

        return user;
    }

    /**
     * 通过学号查找学生，允许该学生不存在
     * @param studentId
     * @return
     */
    public User findOneStudent(String studentId){
        if(studentId == null){
            throw new MyException("学号为空");
        }
        User user = userRepository.findOne(studentId);

        return user;
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public Page<User> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }

    /**
     * 新建用户
     * @param user
     * @return
     */
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    /**
     * 删除用户
     * @param studentId
     */
    @Override
    public void delete(String studentId) {
        userRepository.delete(studentId);
    }

    /**
     * 更新用户的组号
     * @param studentId
     * @param group
     * @return
     */
    @Override
    public User updateGroup(String studentId, Integer group) {
        User user = findOne(studentId);

        if(group < 0){
            throw new MyException(ResultEnum.GROUP_ERROR);
        }
        user.setStudentGroup(group);

        return userRepository.save(user);
    }

    /**
     * 修改用户的密码，新密码在手机端就经过验证
     * @param studentId
     * @param password
     * @return
     */
    @Override
    public User updatePassword(String studentId, String password) {
        User user = findOne(studentId);
        user.setPassword(password);

        return userRepository.save(user);
    }
}
