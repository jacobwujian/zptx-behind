package com.ecit.edu.zpxtbehind.user.bean;

import java.util.Date;

public class User {
    private int pk_user;
    private String userName;
    private String userType;
    private String password;
    private Date ts;
    private Date modifiedtime;

    public int getPk_user() {
        return pk_user;
    }

    public void setPk_user(int pk_user) {
        this.pk_user = pk_user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
