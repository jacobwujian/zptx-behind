package com.ecit.edu.zpxtbehind.resume.bean;

import java.util.Date;

public class Resume {
    private Integer pk_resume;
    private Integer pk_user;
    private String name;
    private String sex;
    private Integer age;
    private String email;
    private String phone;
    private String city;
    private String job;
    private String school1;
    private String school2;
    private String school3;
    private String schoolType1;
    private String schoolType2;
    private String schoolType3;
    private Date start1;
    private Date start2;
    private Date start3;
    private Date end1;
    private Date end2;
    private Date end3;
    private Date ts;
    private Date modifiedtime;
    private String introduction;
    private Byte[] picture;
    private String IDCard;

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }


    public Integer getPk_resume() {
        return pk_resume;
    }

    public void setPk_resume(Integer pk_resume) {
        this.pk_resume = pk_resume;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSchool1() {
        return school1;
    }

    public void setSchool1(String school1) {
        this.school1 = school1;
    }

    public String getSchool2() {
        return school2;
    }

    public void setSchool2(String school2) {
        this.school2 = school2;
    }

    public String getSchool3() {
        return school3;
    }

    public void setSchool3(String school3) {
        this.school3 = school3;
    }

    public String getSchoolType1() {
        return schoolType1;
    }

    public void setSchoolType1(String schoolType1) {
        this.schoolType1 = schoolType1;
    }

    public String getSchoolType2() {
        return schoolType2;
    }

    public void setSchoolType2(String schoolType2) {
        this.schoolType2 = schoolType2;
    }

    public String getSchoolType3() {
        return schoolType3;
    }

    public void setSchoolType3(String schoolType3) {
        this.schoolType3 = schoolType3;
    }

    public Date getStart1() {
        return start1;
    }

    public void setStart1(Date start1) {
        this.start1 = start1;
    }

    public Date getStart2() {
        return start2;
    }

    public void setStart2(Date start2) {
        this.start2 = start2;
    }

    public Date getStart3() {
        return start3;
    }

    public void setStart3(Date start3) {
        this.start3 = start3;
    }

    public Date getEnd1() {
        return end1;
    }

    public void setEnd1(Date end1) {
        this.end1 = end1;
    }

    public Date getEnd2() {
        return end2;
    }

    public void setEnd2(Date end2) {
        this.end2 = end2;
    }

    public Date getEnd3() {
        return end3;
    }

    public void setEnd3(Date end3) {
        this.end3 = end3;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Byte[] getPicture() {
        return picture;
    }

    public void setPicture(Byte[] picture) {
        this.picture = picture;
    }
}
