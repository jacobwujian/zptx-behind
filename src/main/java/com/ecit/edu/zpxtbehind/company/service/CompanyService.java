package com.ecit.edu.zpxtbehind.company.service;


import com.ecit.edu.zpxtbehind.company.bean.Company;
import com.ecit.edu.zpxtbehind.company.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CompanyService {
    @Resource
    CompanyMapper companyMapper;
    public void insertCompany(Integer pk_user){
        companyMapper.insertCompany(pk_user);
    }
    public void deleteCompany(Integer pk_user){
        companyMapper.deleteCompany(pk_user);
    }
    public Company selectCompany(Integer pk_user){
     return    companyMapper.selectCompany(pk_user);
    }
    public void updateCompany(Company company){
        companyMapper.updateCompany(company);
    }
}
