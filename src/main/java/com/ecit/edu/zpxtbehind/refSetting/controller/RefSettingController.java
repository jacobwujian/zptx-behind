package com.ecit.edu.zpxtbehind.refSetting.controller;

import com.alibaba.fastjson.JSONObject;
import com.ecit.edu.zpxtbehind.refSetting.bean.RefTypeSetting;
import com.ecit.edu.zpxtbehind.refSetting.service.RefSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ref")
public class RefSettingController {

    @Autowired
    private RefSettingService refSettingService;

    // 获取所有用户信息
    @ResponseBody
    @RequestMapping("getAllTypes")
    public String getAllUsers() {
        List<RefTypeSetting> users = refSettingService.getAllType();
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("data", users);
        return result.toJSONString();
    }



}
