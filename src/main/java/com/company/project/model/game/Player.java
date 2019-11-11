package com.company.project.model.game;

import java.util.Date;
import javax.persistence.*;

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @Column(name = "open_id")
    private String openId;
    @Column(name = "weixin_id")
    private String weiXinId;
    @Column(name = "weixin2_id")
    private String weiXin2Id;
    @Column(name = "order_state")
    private int orderState;
    @Column(name = "game_state")
    private int gameState;
    @Column(name = "myteam_name")
    private String myTeamName;
    @Column(name = "team_name")
    private String teamName;
    @Column(name = "time")
    private String time;
    @Column(name = "token")
    private String token;
    @Column(name = "formId")
    private String formId;

    public Player() {

    }

    public Player(Integer orderId, String weiXinId, String weiXin2Id, Integer orderState,Integer gameState, String myTeamName, String teamName, String time) {
        this.orderId = orderId;
        this.weiXinId = weiXinId;
        this.weiXin2Id = weiXin2Id;
        this.orderState = orderState;
        this.gameState=gameState;
        this.myTeamName = myTeamName;
        this.teamName = teamName;
        this.time = time;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWeiXinId() {
        return weiXinId;
    }

    public void setWeiXinId(String weiXinId) {
        this.weiXinId = weiXinId;
    }

    public String getWeiXin2Id() {
        return weiXin2Id;
    }

    public void setWeiXin2Id(String weiXin2Id) {
        this.weiXin2Id = weiXin2Id;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public String getMyTeamName() {
        return myTeamName;
    }

    public void setMyTeamName(String myTeamName) {
        this.myTeamName = myTeamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }
}
