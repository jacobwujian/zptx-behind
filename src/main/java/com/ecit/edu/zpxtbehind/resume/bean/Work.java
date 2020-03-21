package com.ecit.edu.zpxtbehind.resume.bean;

import java.util.Date;

public class Work {
    private Integer pk_work;
    private Integer pk_user;
    private String companyName;

    private String job;
    private Date startTime;
    private Date endTime;
    private String workIntroduction;
    private Date ts;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getPk_work() {
        return pk_work;
    }

    public void setPk_work(Integer pk_work) {
        this.pk_work = pk_work;
    }

    public Integer getPk_user() {
        return pk_user;
    }

    public void setPk_user(Integer pk_user) {
        this.pk_user = pk_user;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getWorkIntroduction() {
        return workIntroduction;
    }

    public void setWorkIntroduction(String workIntroduction) {
        this.workIntroduction = workIntroduction;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
}
