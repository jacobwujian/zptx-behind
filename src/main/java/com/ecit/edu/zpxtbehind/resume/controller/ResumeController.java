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
