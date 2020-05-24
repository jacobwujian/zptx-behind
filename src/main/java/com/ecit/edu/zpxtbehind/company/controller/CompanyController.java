package com.ecit.edu.zpxtbehind.company.controller;

import com.alibaba.fastjson.JSONObject;
import com.ecit.edu.zpxtbehind.HeaderParamUtil;
import com.ecit.edu.zpxtbehind.company.bean.Company;
import com.ecit.edu.zpxtbehind.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    HttpServletRequest request;

    private Integer getPk_user() {
        return HeaderParamUtil.getPKUser(request);
    }
    @Autowired
    private CompanyService companyService;

    // 获取公司信息
    @ResponseBody
    @RequestMapping("getCompany")
    public String getCompany(@RequestBody JSONObject jsonParam) {
        Company company;
        if (jsonParam.get("pk_user")!=null){
            company = companyService.selectCompany((Integer) jsonParam.get("pk_user"));

        }else {
            company = companyService.selectCompany(getPk_user());
        }
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("company", company);
        return result.toJSONString();
    }
    // 插入公司信息
    @ResponseBody
    @RequestMapping("insertCompany")
    public String insertCompany(@RequestBody JSONObject jsonParam) {
        companyService.insertCompany((Integer) jsonParam.get("pk_user"));
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 刪除參公司信息
    @ResponseBody
    @RequestMapping("deleteCompany")
    public String deleteCompany(@RequestBody JSONObject jsonParam) {
        companyService.deleteCompany((Integer) jsonParam.get("pk_user"));
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }
    // 修改參公司信息
    @ResponseBody
    @RequestMapping("updateCompany")
    public String updateCompany(@RequestBody JSONObject jsonParam) {
        Company company = new Company();
        company.setPk_user(getPk_user());
        if (jsonParam.get("name")!=null){
            company.setName((String) jsonParam.get("name"));
        }
        if (jsonParam.get("address")!=null){
            company.setAddress((String) jsonParam.get("address"));
        }
        if (jsonParam.get("introduction")!=null){
            company.setIntroduction((String) jsonParam.get("introduction"));
        }
        companyService.updateCompany(company);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

}
