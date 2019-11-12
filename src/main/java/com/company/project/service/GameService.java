package com.company.project.service;

import java.util.List;

import com.company.project.model.game.Player;

public interface GameService {
    /**
     * 添加一个游戏约场信息
     *
     * @param player 游戏约场信息
     */
    void addGameOrder(Player player);

    /**
     * 更新约场信息
     *
     * @param player 约场信息
     */
    Integer updateGameOrder(Player player);

    /**
     * 更新约场信息
     * 即更新状态
     * @param player 约场信息
     */
    Integer updateGameOrderOfSate(Player player);

    /**
     * 获取状态接口
     *
     * @param orderId 约场信息
     */
    Integer getGameOrderStateById(int orderId);

    /**
     * 删除约场信息（撤销接口）
     *
     * @param orderId 约场信息
     */
    Integer deleteGameOrder(Integer orderId);

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
    List<Player> getOtherGameOrder();

    /**
     * 获取我的发起信息接口
     *
     * @return list
     */
    List<Player> getMyGameOrder();

    /**
     * 获取全部信息
     *
     * @return list
     */
    List<Player> getAll();

    /**
     * 根据order_id查询到当前的order
     *
     * @param orderId
     * @return
     */
    Player selectByID(Integer orderId);
}
