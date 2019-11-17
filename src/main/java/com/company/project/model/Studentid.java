package com.company.project.model;

import javax.persistence.*;

public class Studentid {
    @Id
    @Column(name = "studentId")
    private String studentid;

    @Column(name = "openId")
    private String openid;

    @Column(name = "Name")
    private String name;

    @Column(name = "className")
    private String classname;

    /**
     * @return studentId
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
     * @return openId
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
     * @return className
     */
    public String getClassname() {
        return classname;
    }

    /**
     * @param classname
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }
}