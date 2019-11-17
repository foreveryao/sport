package com.company.project.dao;

import java.util.List;

import com.company.project.model.Order;

public interface OrderMapper{
    //添加订场信息
    int insert(Order order);
    //添加订场信息（如果存在空值就跳过该属性）
    int insertSelective(Order order);
    //通过编号来删除约场信息，这是撤销接口的逻辑
    int deleteByOrderId(Integer orderId);
    //根据编号找到该约场信息
    Order selectByID(Integer orderId);
    //根据状态筛选数据库的约场信息
    List<Order> selectByState(Integer orderState);
    //获取全部的约场信息
    List<Order> getAllOrder();
    //根据ID更新约场信息
    int updateByID(Order order);
    //根据ID更新约场信息（如果存在空值就跳过该属性）
    int updateByIDSelective(Order order);
    //根据ID更新约场信息（只更新状态值）
    int updateByIDSelectiveOfSate(Order order);
    //根据ID获取该约场信息的状态
    Order getStateByID(int orderId);
    //根据ID获取约场信息时间
    String getTimeById(int orderId);
}