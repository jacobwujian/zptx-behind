package com.ecit.edu.zpxtbehind.result.controller;

import com.alibaba.fastjson.JSONObject;
import com.ecit.edu.zpxtbehind.result.bean.Result;
import com.ecit.edu.zpxtbehind.result.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Objects;


@RestController
@RequestMapping("/result")
public class ResultController {
    @Autowired
    private ResultService resultService;
    @Autowired
    private RestTemplate restTemplate;

    // 获取活动结果
    @ResponseBody
    @RequestMapping("getResult")
    public String getResult(@RequestBody JSONObject jsonParam) throws Exception {
        Integer pk_act = (Integer) jsonParam.get("pk_act");

        Result res = resultService.selectResult(pk_act);

        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("res", res);
        return result.toJSONString();
    }

    // 获取活动结果
    @ResponseBody
    @RequestMapping("getResss")
    public String getResss(@RequestBody JSONObject jsonParam) throws Exception {
        Integer pk_act = (Integer) jsonParam.get("pk_act");

        Result res = resultService.selectResult(pk_act);

        JSONObject example = new JSONObject();
        example.put("example", "pk_resume in "+res.getResultCount());
        return  Objects.requireNonNull(restTemplate.postForObject("http://127.0.0.1:8123/behind/api/resume/getResumes", example, JSONObject.class)).toJSONString();
    }

    // 插入活动结果
    @ResponseBody
    @RequestMapping("insertResult")
    public String insertResult(@RequestBody JSONObject jsonParam) throws Exception {

        Integer act = (Integer) jsonParam.get("pk_act");
        Result res = new Result();
        res.setPk_act(act);
        resultService.insertResult(res);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 修改活动结果
    @ResponseBody
    @RequestMapping("updateResult")
    public String updateResult(@RequestBody JSONObject jsonParam) throws Exception {
        Integer act = (Integer) jsonParam.get("pk_act");
        String resultCount = (String) jsonParam.get("resultCount");
        Result res = new Result();
        res.setPk_act(act);
        res.setResultCount(resultCount);
        resultService.updateResult(res);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 删除活动结果
    @ResponseBody
    @RequestMapping("deleteResult")
    public String deleteResult(@RequestBody JSONObject jsonParam) throws Exception {
        Integer act = (Integer) jsonParam.get("pk_act");
        resultService.deleteResult(act);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }
}
