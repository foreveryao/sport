package com.company.project.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.company.project.service.OrderService;
import org.springframework.stereotype.Service;
import com.company.project.model.Order;
import com.company.project.dao.OrderMapper;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderDao;

    public void addOrder(Order order) {
        // TODO Auto-generated method stub
        orderDao.insert(order);
    }

    public Integer updateOrder(Order order) {
        // TODO Auto-generated method stub
        return orderDao.updateByIDSelective(order);
    }

    @Override
    public Integer updateOrderOfSate(Order order) {
        return orderDao.updateByIDSelectiveOfSate(order);
    }

    public Order getOrderStateById(int orderId) {
        // TODO Auto-generated method stub
        return this.orderDao.getStateByID(orderId);
    }

    public Integer deleteOrder(Integer orderId) {
        // TODO Auto-generated method stub
        return orderDao.deleteByOrderId(orderId);
    }

    public List<Order> getOtherOrder() {
        // TODO Auto-generated method stub
        List<Order> res;
        res = this.orderDao.selectByState(0);
        res.addAll(0, this.orderDao.selectByState(1));
        return res;
    }

    public List<Order> getMyOrder() {
        // TODO Auto-generated method stub
        return this.orderDao.selectByState(2);
    }

    public List<Order> getAll() {
        return this.orderDao.getAllOrder();
    }

    @Override
    public Order selectByID(Integer orderId) {
        return orderDao.selectByID(orderId);
    }

    @Override
    public String getTimeById(Integer orderId) {
        // TODO Auto-generated method stub
        return this.orderDao.getTimeById(orderId);
    }
}
