package com.ecit.edu.zpxtbehind.resume.controller;

import com.alibaba.fastjson.JSONObject;
import com.ecit.edu.zpxtbehind.resume.bean.Resume;
import com.ecit.edu.zpxtbehind.resume.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    ResumeService resumeService;


    // 插入用户简历
    @ResponseBody
    @RequestMapping("insertResume")
    public String insertResume(@RequestBody JSONObject jsonParam) {
        String name = (String) jsonParam.get("name");
        String IDCard = (String) jsonParam.get("IDCard");
        String phone = (String) jsonParam.get("phone");
        Resume resume = new Resume();
        resume.setName(name);
        resume.setIDCard(IDCard);
        resume.setPhone(phone);
        resumeService.insertResume(resume);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 更新用户简历
    @ResponseBody
    @RequestMapping("updateResume")
    public String updateResume(@RequestBody JSONObject jsonParam) {
        Resume resume = new Resume();
        if (jsonParam.get("pk_resume") != null) {
            Integer pk_resume = (Integer) jsonParam.get("pk_resume");
            resume.setPk_resume(pk_resume);
        }
        if (jsonParam.get("pk_user") != null) {
            Integer pk_user = (Integer) jsonParam.get("pk_user");
            resume.setPk_user(pk_user);
        }
        if (jsonParam.get("name") != null) {
            String name = (String) jsonParam.get("name");
            resume.setName(name);
        }
        if (jsonParam.get("sex") != null) {
            String sex = (String) jsonParam.get("sex");
            resume.setSex(sex);
        }
        if (jsonParam.get("age") != null) {
            Integer age = (Integer) jsonParam.get("age");
            resume.setAge(age);
        }
        if (jsonParam.get("email") != null) {
            String email = (String) jsonParam.get("email");
            resume.setEmail(email);
        }
        if (jsonParam.get("phone") != null) {
            String phone = (String) jsonParam.get("phone");
            resume.setPhone(phone);
        }
        if (jsonParam.get("city") != null) {
            String city = ((ArrayList) jsonParam.get("city")).toString();
            resume.setCity(city);
        }
        if (jsonParam.get("job") != null) {
            String job = (String) jsonParam.get("job");
            resume.setJob(job);
        }
        if (jsonParam.get("IDCard") != null) {
            String IDCard = (String) jsonParam.get("IDCard");
            resume.setIDCard(IDCard);
        }
        if (jsonParam.get("introduction") != null) {
            String introduction = (String) jsonParam.get("introduction");
            resume.setIntroduction(introduction);
        }
        if (jsonParam.get("school1") != null) {
            String school1 = (String) jsonParam.get("school1");
            resume.setSchool1(school1);
        }
        if (jsonParam.get("school2") != null) {
            String school2 = (String) jsonParam.get("school2");
            resume.setSchool2(school2);
        }
        if (jsonParam.get("school3") != null) {
            String school3 = (String) jsonParam.get("school3");
            resume.setSchool3(school3);
        }
        if (jsonParam.get("schoolType1") != null) {
            String schoolType1 = (String) jsonParam.get("schoolType1");
            resume.setSchoolType1(schoolType1);
        }
        if (jsonParam.get("schoolType2") != null) {
            String schoolType2 = (String) jsonParam.get("schoolType2");
            resume.setSchoolType2(schoolType2);
        }
        if (jsonParam.get("schoolType3") != null) {
            String schoolType3 = (String) jsonParam.get("schoolType3");
            resume.setSchoolType3(schoolType3);
        }
        if (jsonParam.get("start1") != null) {
            Date start1 = new Date((long)jsonParam.get("start1"));
            resume.setStart1(start1);
        }
        if (jsonParam.get("start2") != null) {
            Date start2 = new Date((long)jsonParam.get("start2"));
            resume.setStart2(start2);
        }
        if (jsonParam.get("start3") != null) {
            Date start3 = new Date((long)jsonParam.get("start3"));
            resume.setStart3(start3);
        }
        if (jsonParam.get("end1") != null) {
            Date end1 = new Date((long)jsonParam.get("end1"));
            resume.setEnd1(end1);
        }
        if (jsonParam.get("end2") != null) {
            Date end2 = new Date((long)jsonParam.get("end2"));
            resume.setEnd2(end2);
        }
        if (jsonParam.get("end3") != null) {
            Date end3 = new Date((long)jsonParam.get("end3"));
            resume.setEnd3(end3);
        }
        resumeService.updateResume(resume);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 根据用户主键用户简历
    @ResponseBody
    @RequestMapping("getResume")
    public String getResume(@RequestBody JSONObject jsonParam) {
        int pk_user = (int) jsonParam.get("pk_user");
        Resume resume = resumeService.getResumeByPk_user(pk_user);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("data", resume);
        return result.toJSONString();
    }
}
