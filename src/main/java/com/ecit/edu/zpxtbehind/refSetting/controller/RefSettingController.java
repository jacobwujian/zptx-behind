package com.ecit.edu.zpxtbehind.refSetting.controller;

import com.alibaba.fastjson.JSONObject;
import com.ecit.edu.zpxtbehind.refSetting.bean.RefTypeSetting;
import com.ecit.edu.zpxtbehind.refSetting.service.RefSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ref")
public class RefSettingController {

    @Autowired
    private RefSettingService refSettingService;

    // 获取所有用户信息
    @ResponseBody
    @RequestMapping("getAllTypes")
    public String getAllTypes() {
        List<RefTypeSetting> types = refSettingService.getAllType();
        return arrayParse(types);
    }
    // 插入參照分類
    @ResponseBody
    @RequestMapping("insertType")
    public String insertType(@RequestBody JSONObject jsonParam) {
        RefTypeSetting refTypeSetting = new RefTypeSetting();
        refTypeSetting.setName((String) jsonParam.get("appendName"));
        if (jsonParam.get("parent") != null) {
            refTypeSetting.setParent((Integer) jsonParam.get("parent"));
        }
        refTypeSetting = refSettingService.insertType(refTypeSetting);
        JSONObject data = new JSONObject();
        data.put("id",refTypeSetting.getPk_type());
        data.put("label",refTypeSetting.getName());
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("data", data);
        return result.toJSONString();
    }

    // 刪除參照分類
    @ResponseBody
    @RequestMapping("deleteType")
    public String deleteType(@RequestBody JSONObject jsonParam) {
        RefTypeSetting refTypeSetting = new RefTypeSetting();
        refTypeSetting.setPk_type((Integer) jsonParam.get("pk_type"));
        refSettingService.deleteType(refTypeSetting);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 获取所有用户信息
    @ResponseBody
    @RequestMapping("setChildren")
    public String setChildren() {
        int pk_type = 1;
        RefTypeSetting refType = new RefTypeSetting();
        refType.setParent(pk_type);
        RefTypeSetting refTypeSetting=refSettingService.getType(refType);
       ArrayList arrayList= refTypeSetting.getChildrenArry();
        if (arrayList == null){
            arrayList = new ArrayList();
            arrayList.add(2);
            arrayList.add(3);
        }else {
            for (int i=0;i<arrayList.size();i++){
                arrayList.add(2);
                arrayList.add(3);
            }
        }
        refTypeSetting.setChildrenArry(arrayList);

        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 获取所有用户信息
    @ResponseBody
    @RequestMapping("getChildren")
    public String getChildren(@RequestBody JSONObject jsonParam) {
        int parent = (int) jsonParam.get("parent");
        RefTypeSetting refTypeSetting = new RefTypeSetting();
        refTypeSetting.setParent(parent);
        List<RefTypeSetting> types = refSettingService.selectAllRefTypeByParent(refTypeSetting);
       return arrayParse(types);
    }

    private String arrayParse(List<RefTypeSetting> types) {
        ArrayList arrayList =new ArrayList();
        for (RefTypeSetting refTypeSetting: types){
            JSONObject data = new JSONObject();
            data.put("id",refTypeSetting.getPk_type());
            data.put("label",refTypeSetting.getName());
            ArrayList arr=refTypeSetting.getChildrenArry();
            data.put("children", arr);
            arrayList.add(data);
        }
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("data", arrayList);
        return result.toJSONString();
    }
}
