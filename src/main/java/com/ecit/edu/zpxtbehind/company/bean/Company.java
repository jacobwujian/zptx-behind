package com.ecit.edu.zpxtbehind.company.bean;

import java.util.Date;

public class Company {
    private Integer pk_company;
    private Integer pk_user;
    private String name;
    private String address;
    private String introduction;
    private Date ts;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPk_company() {
        return pk_company;
    }

    public void setPk_company(Integer pk_company) {
        this.pk_company = pk_company;
    }

    public Integer getPk_user() {
        return pk_user;
    }

    public void setPk_user(Integer pk_user) {
        this.pk_user = pk_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
}
