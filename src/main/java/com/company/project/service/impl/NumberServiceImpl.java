package com.company.project.service.impl;

import com.company.project.dao.NumberMapper;
import com.company.project.model.number;
import com.company.project.service.NumberService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/11/07.
 */
@Service
@Transactional
public class NumberServiceImpl extends AbstractService<number> implements NumberService {
    @Resource
    private NumberMapper numberMapper;

}
