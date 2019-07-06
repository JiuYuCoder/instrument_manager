package com.gis.measure.service.impl;

import com.gis.measure.dataobject.Manager;
import com.gis.measure.enums.ResultEnum;
import com.gis.measure.exception.MyException;
import com.gis.measure.repository.ManagerRepository;
import com.gis.measure.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    /**
     * 使用学号查询管理员
     * @param studentId
     * @return
     */
    @Override
    public Manager findOne(String studentId) {
        if(StringUtils.isEmpty(studentId)){
            throw new MyException("学号为空");
        }
        Manager manager = managerRepository.findOne(studentId);
        if(manager == null){
            throw new MyException(ResultEnum.MANAGER_NOT_EXIT);
        }
        return manager;
    }

    /**
     * 返回所有管理员
     * @return
     */
    @Override
    public Page<Manager> findAll(Pageable pageable) {
        return managerRepository.findAll(pageable);
    }

    /**
     * 创建管理员
     * @param manager
     * @return
     */
    @Override
    public Manager create(Manager manager) {
        return managerRepository.save(manager);
    }

    /**
     * 删除管理员
     * @param studentId
     */
    @Override
    public void delete(String studentId) {
        managerRepository.delete(studentId);
    }
}
