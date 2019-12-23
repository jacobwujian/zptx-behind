package com.ecit.edu.zpxtbehind.user.bean;

import java.util.Date;

public class UserInfo {
    private int pk_userInfo;
    private int pk_user;
    private String name;
    private int age;
    private String userInfo;
    private String school;
    private String phone;
    private String IDCard;
    private Date ts;
    private Date modifiedtime;
    public int getPk_userInfo() {
        return pk_userInfo;
    }

    public void setPk_userInfo(int pk_userInfo) {
        this.pk_userInfo = pk_userInfo;
    }

    public int getPk_user() {
        return pk_user;
    }

    public void setPk_user(int pk_user) {
        this.pk_user = pk_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Date getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(Date modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

}
