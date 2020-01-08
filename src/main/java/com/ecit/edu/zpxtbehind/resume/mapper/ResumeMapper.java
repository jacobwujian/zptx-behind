package com.ecit.edu.zpxtbehind.resume.mapper;

import com.ecit.edu.zpxtbehind.resume.bean.Resume;

public interface ResumeMapper {
    void insertResume(Resume resume);
    void updateResume(Resume resume);
    Resume getResumeByPk_user(int pk_user);
}
