package com.company.project.service;

import java.util.List;

import com.company.project.model.Order;

public interface OrderService {
    /**
     * 添加一个约场信息
     *
     * @param order 约场信息
     */
    void addOrder(Order order);

    /**
     * 更新约场信息
     *
     * @param order 约场信息
     */
    Integer updateOrder(Order order);

    /**
     * 更新约场信息
     * 即更新状态
     * @param order 约场信息
     */
    Integer updateOrderOfSate(Order order);

    /**
     * 获取状态接口
     *
     * @param orderId 约场信息
     */
    Order getOrderStateById(int orderId);

    /**
     * 删除约场信息（撤销接口）
     *
     * @param orderId 约场信息
     */
    Integer deleteOrder(Integer orderId);

    /**
     * 微信获取授权接口
     * @param
     */

    /**
     * 获取时间接口
     *
     * @param
     */
    String getTimeById(Integer orderId);

    /**
     * 获取对方发起信息接口
     *
     * @return list
     */
    List<Order> getOtherOrder();

    /**
     * 获取我的发起信息接口
     *
     * @return list
     */
    List<Order> getMyOrder();

    /**
     * 获取全部信息
     *
     * @return list
     */
    List<Order> getAll();

    /**
     * 根据order_id查询到当前的order
     *
     * @param orderId
     * @return
     */
    Order selectByID(Integer orderId);
}
