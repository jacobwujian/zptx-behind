package com.ecit.edu.zpxtbehind.resume.service;

import com.ecit.edu.zpxtbehind.resume.bean.Resume;
import com.ecit.edu.zpxtbehind.resume.mapper.ResumeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

   public void deleteResume(int pk_user){
       resumeMapper.deleteResume(pk_user);
   }
   public String getSkills(int pk_user){
        return resumeMapper.getSkills(pk_user);
   }
   public void updateSkills(String skills){
       resumeMapper.updateSkills(skills);
   }
    public List<Resume> getResumes(String example){
        return resumeMapper.getResumes(example);
    }
}
