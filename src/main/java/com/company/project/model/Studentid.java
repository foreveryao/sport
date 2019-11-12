package com.company.project.model;

import javax.persistence.*;

public class Studentid {
    @Id
    @Column(name = "studentID")
    private String studentid;

    @Column(name = "openID")
    private String openid;

    @Column(name = "Name")
    private String name;

    @Column(name = "class")
    private String class1;

    public Studentid() {

    }

    public Studentid(String studentid,String openid,String name,String class1) {
        this.studentid=studentid;
        this.openid=openid;
        this.name=name;
        this.class1=class1;
    }

    /**
     * @return studentID
     */
    public String getStudentid() {
        return studentid;
    }

    /**
     * @param studentid
     */
    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    /**
     * @return openID
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * @param openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return class1
     */
    public String getClass1() {
        return class1;
    }

    /**
     * @param class1
     */
    public void setClass(String class1) {
        this.class1 = class1;
    }
}