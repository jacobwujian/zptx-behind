package com.ecit.edu.zpxtbehind.resume.mapper;

import com.ecit.edu.zpxtbehind.resume.bean.Resume;
import com.ecit.edu.zpxtbehind.resume.bean.Work;

import java.util.List;

public interface ResumeMapper {
    void insertResume(Resume resume);
    void updateResume(Resume resume);
    Resume getResumeByPk_user(int pk_user);
    List<Resume> getResumes(String example);
    void deleteResume(int pk_user);
    String getSkills(int pk_user);
    void updateSkills(Resume resume);

    void insertWork(Work work);
    void updateWork(Work work);
    void deleteWork(Work work);
    List<Work> getWorks(Work work);
}
