package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Order;
import com.company.project.model.Studentid;

public interface StudentidMapper extends Mapper<Studentid> {
    int updateByIDSelective(Studentid order);
}