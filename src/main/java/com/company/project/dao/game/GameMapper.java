package com.company.project.dao.game;

import java.util.List;

import com.company.project.model.game.Player;

public interface GameMapper{
    //添加游戏信息
    int insert(Player player);
    //通过编号来删除游戏信息，这是撤销接口的逻辑
    int deleteByPlayerId(Integer orderId);
    //根据编号找到该游戏信息
    Player selectByID(Integer orderId);
    //根据信息状态筛选数据库的约场信息
    List<Player> selectByOrderState(Integer orderState);
    //获取全部的约场信息
    List<Player> getAllPlayers();
    //根据ID更新约场信息
    int updateByID(Player player);
    //根据ID更新约场信息（如果存在空值就跳过该属性）
    int updateByIDSelective(Player player);
    //根据ID更新约场信息（只更新状态值）
    int updateByIDSelectiveOfSate(Player player);
    //根据ID获取该约场信息的状态
    Integer getStateByID(int orderId);
    //根据ID获取约场信息时间
    String getTimeById(int orderId);
}