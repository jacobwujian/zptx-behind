package com.ecit.edu.zpxtbehind.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.ecit.edu.zpxtbehind.user.bean.User;
import com.ecit.edu.zpxtbehind.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.List;

import static com.ecit.edu.zpxtbehind.user.EncryptHelper.encrypt;

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

    // 插入用户信息
    @ResponseBody
    @RequestMapping("insertUser")
    public String insertUser(@RequestBody JSONObject jsonParam) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException {
        String username=(String)jsonParam.get("username");
        String password = (String)jsonParam.get("password");
        User user=new User();
        user.setUserName(username);
        password = encrypt(password);
        user.setUserType("0");
        user.setPassword(password);
        userService.insertUser(user);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 修改用户密码
    @ResponseBody
    @RequestMapping("updateUserPassword")
    public String updateUserPassword(@RequestBody JSONObject jsonParam) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException {
        String username=(String)jsonParam.get("username");
        String password = (String)jsonParam.get("password");
        User user=new User();
        user.setUserName(username);
        Base64.Decoder decoder = Base64.getDecoder();
        String pw = new String(decoder.decode(password));
        password = encrypt(username+pw);
        user.setPassword(password);
        userService.updateUserPassword(user);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 用户登陆校验
    @ResponseBody
    @RequestMapping("login")
    public String login(@RequestBody JSONObject jsonParam) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException {
        User user=new User();
        String username=(String)jsonParam.get("username");
        user.setUserName(username);
        String password = (String)jsonParam.get("password");
        Base64.Decoder decoder = Base64.getDecoder();
        String pw = new String(decoder.decode(password));
        user.setPassword(username+pw);
        Boolean isTrue=userService.login(user);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("result", isTrue);
        return result.toJSONString();
    }
}
