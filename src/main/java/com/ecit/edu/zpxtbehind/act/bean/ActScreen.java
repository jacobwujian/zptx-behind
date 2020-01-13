package com.ecit.edu.zpxtbehind.act.bean;

import java.util.Date;

public class ActScreen {
    private Integer pk_screen;
    private Integer pk_act;
    private String filedName;
    private String screenCoin;
    private String screenValue;
    private Date ts;

    public Integer getPk_screen() {
        return pk_screen;
    }

    public void setPk_screen(Integer pk_screen) {
        this.pk_screen = pk_screen;
    }

    public Integer getPk_act() {
        return pk_act;
    }

    public void setPk_act(Integer pk_act) {
        this.pk_act = pk_act;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getScreenCoin() {
        return screenCoin;
    }

    public void setScreenCoin(String screenCoin) {
        this.screenCoin = screenCoin;
    }

    public String getScreenValue() {
        return screenValue;
    }

    public void setScreenValue(String screenValue) {
        this.screenValue = screenValue;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
}
