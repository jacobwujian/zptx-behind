package com.ecit.edu.zpxtbehind.resume.controller;

import com.alibaba.fastjson.JSONObject;
import com.ecit.edu.zpxtbehind.HeaderParamUtil;
import com.ecit.edu.zpxtbehind.resume.bean.Resume;
import com.ecit.edu.zpxtbehind.resume.bean.Work;
import com.ecit.edu.zpxtbehind.resume.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    ResumeService resumeService;

    @Autowired
    HttpServletRequest request;

    private Integer getPk_user() {
        return HeaderParamUtil.getPKUser(request);
    }
    // 插入用户简历
    @ResponseBody
    @RequestMapping("insertResume")
    public String insertResume(@RequestBody JSONObject jsonParam) {
        Integer pk_user = (Integer) jsonParam.get("pk_user");
        String name = (String) jsonParam.get("name");
        String phone = (String) jsonParam.get("phone");
        Resume resume = new Resume();
        resume.setName(name);
        resume.setPhone(phone);
        resume.setPk_user(pk_user);
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
        if (getPk_user() != null) {
            Integer pk_user = getPk_user();
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
            String city = (jsonParam.get("city")).toString();
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
        if (jsonParam.get("education") != null) {
            String education = (String) jsonParam.get("education");
            resume.setEducation(education);
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
    public String getResume() {
        int pk_user =  getPk_user();
        Resume resume = resumeService.getResumeByPk_user(pk_user);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("data", resume);
        return result.toJSONString();
    }

    //删除简历
    @ResponseBody
    @RequestMapping("deleteResume")
    public String deleteResume(@RequestBody JSONObject jsonParam) throws IOException {
        resumeService.deleteResume((Integer) jsonParam.get("pk_user"));
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 存图片
    @ResponseBody
    @RequestMapping("setPicture")
    public String setPicture(StandardMultipartHttpServletRequest httpServletRequest) throws IOException {
       String s= httpServletRequest.getQueryString();
       String pk = s.substring(s.indexOf("pk_resume=")+10);
        MultipartFile multipartFile =httpServletRequest.getMultiFileMap().getFirst("file");
        assert multipartFile != null;
        byte[] bytes = multipartFile.getBytes();
        Resume resume = new Resume();
        resume.setPicture(bytes);
        resume.setPk_resume(Integer.valueOf(pk));
        resumeService.updateResume(resume);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    //获取多份简历
    @ResponseBody
    @RequestMapping("getResumes")
    public String getResumes(@RequestBody JSONObject jsonParam) throws IOException {
        String example= (String) jsonParam.get("example");
        List<Resume> list = resumeService.getResumes(example);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("data", list);
        return result.toJSONString();
    }

    //获取技能
    @ResponseBody
    @RequestMapping("getSkills")
    public String getSkills() throws IOException {
        String skills = resumeService.getSkills(getPk_user());
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("data", skills);
        return result.toJSONString();
    }


    //修改技能
    @ResponseBody
    @RequestMapping("updateSkills")
    public String updateSkills(@RequestBody JSONObject jsonParam) {
        Resume resume =new Resume();
        String skills= (String) jsonParam.get("skills");
        resume.setSkills(skills);
        resume.setPk_user(getPk_user());
        resumeService.updateSkills(resume);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }
    private Work getWorkByJsonParam(JSONObject jsonParam){
        Work work =new Work();
        Integer pk_work = (Integer)jsonParam.get("pk_work");
        String companyName= (String) jsonParam.get("companyName");
        String job= (String) jsonParam.get("job");
        Date startTime= new Date((long)( jsonParam.get("startTime")));
        Date endTime= new Date((long)( jsonParam.get("endTime")));
        String workIntroduction = (String) jsonParam.get("workIntroduction");
        work.setCompanyName(companyName);
        work.setStartTime(startTime);
        work.setEndTime(endTime);
        work.setJob(job);
        work.setWorkIntroduction(workIntroduction);
        work.setPk_work(pk_work);
        work.setPk_user(getPk_user());
        return work;
    }
    //增加工作经历
    @ResponseBody
    @RequestMapping("insertWork")
    public String insertWork(@RequestBody JSONObject jsonParam) {
        Work work = getWorkByJsonParam(jsonParam);
        resumeService.insertWork(work);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("work", work);
        return result.toJSONString();
    }
    //修改工作经历
    @ResponseBody
    @RequestMapping("updateWork")
    public String updateWork(@RequestBody JSONObject jsonParam) {
        Work work = getWorkByJsonParam(jsonParam);
        resumeService.updateWork(work);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }
    //删除工作经历
    @ResponseBody
    @RequestMapping("deleteWork")
    public String deleteWork(@RequestBody JSONObject jsonParam) {
        Work work =new Work();
        Integer pk_work= (Integer) jsonParam.get("pk_work");
        work.setPk_work(pk_work);
        resumeService.deleteWork(work);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    //查询工作经历
    @ResponseBody
    @RequestMapping("getWorks")
    public String getWorks(@RequestBody JSONObject jsonParam) {
        Work work =new Work();
        if (jsonParam.get("pk_user")!=null){
            work.setPk_user((Integer) jsonParam.get("pk_user"));
        }else {
            work.setPk_user(getPk_user());
        }
        List works = resumeService.getWorks(work);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("works",works);
        return result.toJSONString();
    }

}
