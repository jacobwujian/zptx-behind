package com.ecit.edu.zpxtbehind.result.bean;

import java.util.Date;

public class Result {
    private Integer pk_act;
    private Integer pk_result;
    private String resultCount;
    private Date ts;

    public Integer getPk_act() {
        return pk_act;
    }

    public void setPk_act(Integer pk_user) {
        this.pk_act = pk_user;
    }

    public Integer getPk_result() {
        return pk_result;
    }

    public void setPk_result(Integer pk_result) {
        this.pk_result = pk_result;
    }

    public String getResultCount() {
        return resultCount;
    }

    public void setResultCount(String resultCount) {
        this.resultCount = resultCount;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
}
