package com.company.project.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class WxUser {
    //用户id
    @Column(name = "id")
    private Long id;
    //手机号
    @Column(name = "url")
    private String url;
    //用户编号
    @Column(name = "weixinId")
    private String weixinId;
    //用户的学号
    @Column(name = "number")
    private String number;
    //开放id
    @Id
    @Column(name = "openId")
    private String openId;
    //微信授权加密key
    @Column(name = "sessionKey")
    private String sessionKey;
    //同一商户下唯一id
    @Column(name = "unionId")
    private String unionId;
    //登录时间
    @Column(name = "loginTime")
    private String loginTime;
    //前端token值
    private String Token;

    public WxUser() {

    }
    public WxUser(String url,String weixinId,String number,String openId,String sessionKey,String unionId,String loginTime) {
        this.url=url;
        this.weixinId=weixinId;
        this.number=number;
        this.openId=openId;
        this.sessionKey=sessionKey;
        this.unionId=unionId;
        this.loginTime=loginTime;
    }

    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionId() {
        return unionId;
    }
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getWeixinId() {
        return weixinId;
    }
    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public String getLoginTime() {
        return loginTime;
    }
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getToken() {return Token;}
    public void setToken(String token) {this.Token=token;}
}
