package com.ecit.edu.zpxtbehind.refSetting.bean;

import java.util.Date;

public class RefSetting {
    private Integer pk_ref;
    private String name;
    private Integer type;
    private Date ts;

    public Integer getPk_ref() {
        return pk_ref;
    }

    public void setPk_ref(Integer pk_ref) {
        this.pk_ref = pk_ref;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
}
