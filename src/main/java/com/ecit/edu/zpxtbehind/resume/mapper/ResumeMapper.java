package com.ecit.edu.zpxtbehind.resume.mapper;

import com.ecit.edu.zpxtbehind.resume.bean.Resume;
import com.ecit.edu.zpxtbehind.resume.bean.UserSkill;

import java.util.List;

public interface ResumeMapper {
    void insertResume(Resume resume);
    void updateResume(Resume resume);
    Resume getResumeByPk_user(int pk_user);
    void deleteResume(int pk_user);
    List<UserSkill> getSkills(int pk_user);
    void insertSkill(UserSkill userSkill);
    void deleteSkills(int pk_user);
    void updateSkill(UserSkill userSkill);
}
