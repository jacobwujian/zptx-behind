package com.ecit.edu.zpxtbehind.company.mapper;

import com.ecit.edu.zpxtbehind.company.bean.Company;


public interface CompanyMapper {
   Company selectCompany(Integer pk_user);
   void insertCompany(Integer pk_user);
   void deleteCompany(Integer pk_user);
   void updateCompany(Company Company);
}
