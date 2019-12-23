package com.ecit.edu.zpxtbehind.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.ecit.edu.zpxtbehind.user.bean.User;
import com.ecit.edu.zpxtbehind.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 获取所有用户信息
    @ResponseBody
    @RequestMapping("getAllUsers")
    public String getAllUsers(HttpServletRequest request) {
        List<User> users = userService.getAllUsers();
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("data", users);
        return result.toJSONString();
    }
}
