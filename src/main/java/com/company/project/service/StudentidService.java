package com.company.project.service;
import com.company.project.model.Order;
import com.company.project.model.Studentid;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2019/11/13.
 */
public interface StudentidService extends Service<Studentid> {
    Integer updateOrder(Studentid order);
}
