package com.company.project.service.impl;

import com.company.project.dao.StudentidMapper;
import com.company.project.model.Order;
import com.company.project.model.Studentid;
import com.company.project.service.StudentidService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/11/13.
 */
@Service
@Transactional
public class StudentidServiceImpl extends AbstractService<Studentid> implements StudentidService {
    @Resource
    private StudentidMapper studentidMapper;
    public Integer updateOrder(Studentid order) {
        // TODO Auto-generated method stub
        return studentidMapper.updateByIDSelective(order);
    }
}
