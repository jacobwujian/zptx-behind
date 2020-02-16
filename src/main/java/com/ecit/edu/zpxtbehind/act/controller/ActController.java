package com.ecit.edu.zpxtbehind.act.controller;

import com.alibaba.fastjson.JSONObject;
import com.ecit.edu.zpxtbehind.HeaderParamUtil;
import com.ecit.edu.zpxtbehind.act.bean.Act;
import com.ecit.edu.zpxtbehind.act.bean.ActScreen;
import com.ecit.edu.zpxtbehind.act.service.ActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/act")
public class ActController {

    @Autowired
    HttpServletRequest request;

    private Integer getPk_user() {
        return HeaderParamUtil.getPKUser(request);
    }
    @Autowired
    ActService actService;

    // 获取活动信息
    @ResponseBody
    @RequestMapping("getActInformation")
    public String getActInformation(@RequestBody JSONObject jsonParam) {
        Integer pk_act = (Integer) jsonParam.get("pk_act");
        Act act = new Act();
        act.setPk_act(pk_act);
        List<ActScreen> actScreens = actService.selectActScreens(act);
        act = actService.selectAct(act);
        JSONObject result = new JSONObject();
        result.put("act", act);
        result.put("actScreens", actScreens);
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 获取所有活动的部分信息
    @ResponseBody
    @RequestMapping("getAllActs")
    public String getAllActs() {
        Integer pk_user = getPk_user();
        List<Act> acts = actService.selectActs(pk_user);
        JSONObject result = new JSONObject();
        result.put("acts", acts);
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 获取所有活动的部分信息
    @ResponseBody
    @RequestMapping("searchByExample")
    public String searchByExample(@RequestBody JSONObject jsonParam) {
        Act act = new Act();
        if (jsonParam.get("searchName")!=null) {
            String searchName = "%"+ jsonParam.get("searchName") + "%";
            act.setAct_name(searchName);
        }
        if (jsonParam.get("chooseState")!=null) {
            Integer state = (Integer) jsonParam.get("chooseState");
            act.setState(state);
        }
        List<Act> acts = actService.selectActsByExample(act);
        JSONObject result = new JSONObject();
        result.put("acts", acts);
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 插入活动信息
    @ResponseBody
    @RequestMapping("insertAct")
    public String insertAct(@RequestBody JSONObject jsonParam) {
        Act act = getActWithRequest(jsonParam);
        actService.insertAct(act);
        JSONObject result = new JSONObject();
        result.put("act", act);
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }
    // 插入活动筛选条件
    @ResponseBody
    @RequestMapping("insertActScreens")
    public String insertActScreens(@RequestBody JSONObject jsonParam) {
        List<ActScreen> actScreens = (List<ActScreen>) jsonParam.get("actScreens");
        actService.insertActScreens(actScreens);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }
    // 插入活动筛选条件
    @ResponseBody
    @RequestMapping("updateAct")
    public String updateAct(@RequestBody JSONObject jsonParam) {
        Act act = getActWithRequest(jsonParam);
        actService.updateAct(act);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }
    // 插入活动筛选条件
    @ResponseBody
    @RequestMapping("deleteScreens")
    public String deleteScreens(@RequestBody JSONObject jsonParam) {
        Act act = getActWithRequest(jsonParam);
        actService.deleteScreens(act);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }
    // 插入活动筛选条件
    @ResponseBody
    @RequestMapping("deleteAct")
    public String deleteAct(@RequestBody JSONObject jsonParam) {
        Act act = getActWithRequest(jsonParam);
        actService.deleteAct(act);
        actService.deleteScreens(act);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    private Act getActWithRequest(JSONObject jsonParam){
        Act act = new Act();
        Integer pk_user = getPk_user();
        act.setPk_user(pk_user);
        if (jsonParam.get("pk_act") != null) {
            act.setPk_act((Integer) jsonParam.get("pk_act"));
        }
        if (jsonParam.get("act_name") != null) {
            act.setAct_name((String) jsonParam.get("act_name"));
        }
        if (jsonParam.get("creator") != null) {
            act.setCreator((String) jsonParam.get("creator"));
        }
        if (jsonParam.get("job") != null) {
            act.setJob((String) jsonParam.get("job"));
        }
        if (jsonParam.get("jobNumber") != null) {
            act.setJobNumber((Integer) jsonParam.get("jobNumber"));
        }
        if (jsonParam.get("company") != null) {
            act.setCompany((String) jsonParam.get("company"));
        }
        if (jsonParam.get("introduction") != null) {
            act.setIntroduction((String) jsonParam.get("introduction"));
        }
        if (jsonParam.get("startTime") != null) {
            act.setStartTime(new Date((long) jsonParam.get("startTime")));
        }
        if (jsonParam.get("endTime") != null) {
            act.setEndTime(new Date((long) jsonParam.get("endTime")));
        }
        if (jsonParam.get("state") != null) {
            act.setState((Integer) jsonParam.get("state"));
        }
        return act;
    }

}
