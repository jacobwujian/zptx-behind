package com.ecit.edu.zpxtbehind.resume.bean;

import java.util.Date;

public class UserSkill {
    private Integer pk_usk;
    private Integer pk_user;
    private String skilName;
    private String skilValue;
    private Date ts;

    public Integer getPk_usk() {
        return pk_usk;
    }

    public void setPk_usk(Integer pk_usk) {
        this.pk_usk = pk_usk;
    }

    public Integer getPk_user() {
        return pk_user;
    }

    public void setPk_user(Integer pk_user) {
        this.pk_user = pk_user;
    }

    public String getSkilName() {
        return skilName;
    }

    public void setSkilName(String skilName) {
        this.skilName = skilName;
    }

    public String getSkilValue() {
        return skilValue;
    }

    public void setSkilValue(String skilValue) {
        this.skilValue = skilValue;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
}
