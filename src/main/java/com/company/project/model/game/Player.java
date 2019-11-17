package com.company.project.model.game;

import java.util.Date;
import javax.persistence.*;
import java.sql.Timestamp;

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
    @Column(name = "time_to_state")
    private Timestamp time_to_state;
    @Column(name = "token")
    private String token;
    @Column(name = "formId")
    private String formId;
    @Column(name = "form2Id")
    private String form2Id;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "remarks_other")
    private String remarks_other;

    public String getRemarks() {
        return remarks;
    }

    public String getRemarks_other() {
        return remarks_other;
    }

    public void setRemarks_other(String remarks_other) {
        this.remarks_other = remarks_other;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

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

    public int getGameState() {
        return gameState;
    }
    public void setGameState(int gameState) {
        this.gameState=gameState;
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

    public Timestamp getTimeToState() {
        return time_to_state;
    }
    public void setTimeToState(Timestamp timeToState) {
        this.time_to_state=timeToState;
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

    public String getForm2Id() {
        return form2Id;
    }
    public void setForm2Id(String form2Id) {
        this.form2Id = form2Id;
    }
}
