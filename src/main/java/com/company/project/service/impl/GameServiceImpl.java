package com.company.project.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.company.project.service.GameService;
import org.springframework.stereotype.Service;
import com.company.project.model.game.Player;
import com.company.project.dao.game.GameMapper;

@Service("GameService")
public class GameServiceImpl implements GameService {
    @Resource
    private GameMapper gameMapper;

    public void addGameOrder(Player player) {
        // TODO Auto-generated method stub
        gameMapper.insert(player);
    }

    public Integer updateGameOrder(Player player) {
        // TODO Auto-generated method stub
        return gameMapper.updateByIDSelective(player);
    }

    @Override
    public Integer updateGameOrderOfSate(Order order) {
        return gameMapper.updateByIDSelectiveOfSate(order);
    }

    public Integer getGameOrderStateById(int orderId) {
        // TODO Auto-generated method stub
        return this.gameMapper.getStateByID(orderId);
    }

    public Integer deleteGameOrder(Integer orderId) {
        // TODO Auto-generated method stub
        return gameMapper.deleteByPlayerId(orderId);
    }

    public List<Order> getOtherGameOrder() {
        // TODO Auto-generated method stub
        List<Order> res;
        res = this.gameMapper.selectByState(0);
        res.addAll(0, this.gameMapper.selectByState(1));
        return res;
    }

    public List<Order> getMyGameOrder() {
        // TODO Auto-generated method stub
        return this.gameMapper.selectByState(2);
    }

    public List<Order> getAll() {
        return this.gameMapper.getAllPlayers();
    }

    @Override
    public Order selectByID(Integer orderId) {
        return gameMapper.selectByID(orderId);
    }

    @Override
    public String getTimeById(Integer orderId) {
        // TODO Auto-generated method stub
        return this.gameMapper.getTimeById(orderId);
    }
}
