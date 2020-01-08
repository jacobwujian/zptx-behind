package com.ecit.edu.zpxtbehind.resume.service;

import com.ecit.edu.zpxtbehind.resume.bean.Resume;
import com.ecit.edu.zpxtbehind.resume.mapper.ResumeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class ResumeService {
    @Resource
    ResumeMapper resumeMapper;
    public void insertResume(Resume resume){
        resumeMapper.insertResume(resume);
    }
    public void updateResume(Resume resume){
        resumeMapper.updateResume(resume);
    }

    public Resume getResumeByPk_user(int pk_user){
        return resumeMapper.getResumeByPk_user(pk_user);
    }
}
