package com.company.project.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class number {
    //学号
    @Id
    @Column(name = "studentID")
    private String id;
    //该学号学生名字
    @Column(name = "Name")
    private String name;
    //该学号学生班级
    @Column(name = "class")
    private String classNum;
    //绑定学号的微信id
    @Column(name = "openID")
    private String openId;

    public number() {

    }
    public number(String id,String name,String classNum,String openId) {
        this.id=id;
        this.name=name;
        this.classNum=classNum;
        this.openId=openId;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }

    public String getClassNum() {
        return classNum;
    }
    public void setClassNum(String classNum) {
        this.classNum=classNum;
    }

    public String getOpendId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId=openId;
    }
}