package com.ecit.edu.zpxtbehind.refSetting.controller;

import com.alibaba.fastjson.JSONObject;
import com.ecit.edu.zpxtbehind.refSetting.bean.RefSetting;
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

    // 获取顶层分类
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
        refSettingService.deleteRefByType(refTypeSetting);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }
    // 参照改名
    @ResponseBody
    @RequestMapping("updateTypeName")
    public String updateTypeName(@RequestBody JSONObject jsonParam) {
        RefTypeSetting refTypeSetting = new RefTypeSetting();
        refTypeSetting.setPk_type((Integer) jsonParam.get("pk_type"));
        refTypeSetting.setName((String) jsonParam.get("name"));
        refSettingService.resetTypeName(refTypeSetting);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 存節點信息
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

    // 获取子節點信息
    @ResponseBody
    @RequestMapping("getChildren")
    public String getChildren(@RequestBody JSONObject jsonParam) {
        Integer parent = null;
        if (jsonParam.get("parent")!=null) {
            parent = (Integer) jsonParam.get("parent");
        }
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
            data.put("value",refTypeSetting.getName());
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

    // 参照改名
    @ResponseBody
    @RequestMapping("updateName")
    public String updateName(@RequestBody JSONObject jsonParam) {
        RefSetting refSetting = new RefSetting();
        refSetting.setPk_ref((Integer) jsonParam.get("pk_ref"));
        refSetting.setName((String) jsonParam.get("name"));
        refSettingService.resetName(refSetting);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 获取参照
    @ResponseBody
    @RequestMapping("selectRef")
    public String selectRef(@RequestBody JSONObject jsonParam) {
        Integer type = (Integer) jsonParam.get("type");
        String name;
        if (jsonParam.get("name")==null||jsonParam.get("name").equals("")){
            name = null;
        }else {
            name = "%"+ jsonParam.get("name")+"%";
        }
        ArrayList arrayList;
        if (jsonParam.get("children")!=null){
             arrayList = (ArrayList) jsonParam.get("children");
        }else {
             arrayList = new ArrayList();
        }
        arrayList.add(type);
        String arr = arrayList.toString().substring(1,arrayList.toString().length()-1);
        arr = "(" +arr +")";
        List<RefSetting>  list=refSettingService.selectInformationByExample(name, arr);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("data", list);
        return result.toJSONString();
    }

    // 刪除參照
    @ResponseBody
    @RequestMapping("deleteRef")
    public String deleteRef(@RequestBody JSONObject jsonParam) {
        RefSetting refSetting = new RefSetting();
        refSetting.setPk_ref((Integer) jsonParam.get("pk_ref"));
        refSettingService.deleteRef(refSetting);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 插入参照
    @ResponseBody
    @RequestMapping("insertRef")
    public String insertRef(@RequestBody JSONObject jsonParam) {
        RefSetting refSetting = new RefSetting();
        refSetting.setType((Integer) jsonParam.get("type"));
        refSetting.setName((String) jsonParam.get("name"));
        refSettingService.insertRef(refSetting);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("data", refSetting);
        return result.toJSONString();
    }
}
