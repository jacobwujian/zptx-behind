package com.ecit.edu.zpxtbehind.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.ecit.edu.zpxtbehind.user.EncryptHelper;
import com.ecit.edu.zpxtbehind.user.bean.User;
import com.ecit.edu.zpxtbehind.user.bean.UserInfo;
import com.ecit.edu.zpxtbehind.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
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
    public String getAllUsers() throws NoSuchPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        List<User> users = userService.getAllUsers();
        List<User> newUsers =new ArrayList<>();
        for (User user:users) {
            String pa=EncryptHelper.unencrypt(user.getPassword()).substring(user.getUserName().length());
            user.setPassword(pa);
            newUsers.add(user);
        }
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("data", newUsers);
        return result.toJSONString();
    }

    // 插入用户信息
    @ResponseBody
    @RequestMapping("insertUser")
    public String insertUser(@RequestBody JSONObject jsonParam) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException {
        String username=(String)jsonParam.get("userName");
        String password = jsonParam.get("userName")+(String)jsonParam.get("password");
        String name = (String)jsonParam.get("name");
        String userType = (String)jsonParam.get("userType");
        String introduction = (String)jsonParam.get("introduction");
        String avatar = (String)jsonParam.get("avatar");
        String IDCard = (String)jsonParam.get("IDCard");
        String phone = (String)jsonParam.get("phone");
        String school = (String)jsonParam.get("school");
        User user=new User();
        user.setUserName(username);
        password = encrypt(password);
        user.setUserType(userType);
        user.setPassword(password);
        user.setName(name);
        user.setIDCard(IDCard);
        user.setPhone(phone);
        user.setSchool(school);
        user.setIntroduction(introduction);
        user.setAvatar(avatar);
        userService.insertUser(user);
        user.setPassword((String)jsonParam.get("password"));
        JSONObject result = new JSONObject();
        result.put("user", user);
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }
    // 插入用户信息
    @ResponseBody
    @RequestMapping("updateUserByPk_user")
    public String updateUserByPk_user(@RequestBody JSONObject jsonParam) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException {
        int pk_user=(Integer)jsonParam.get("pk_user");
        String username=(String)jsonParam.get("userName");
        String password = jsonParam.get("userName")+(String)jsonParam.get("password");
        String name = (String)jsonParam.get("name");
        String userType = (String)jsonParam.get("userType");
        String introduction = (String)jsonParam.get("introduction");
        String avatar = (String)jsonParam.get("avatar");
        String IDCard = (String)jsonParam.get("IDCard");
        String phone = (String)jsonParam.get("phone");
        String school = (String)jsonParam.get("school");

        User user=new User();
        user.setPk_user(pk_user);
        user.setUserName(username);
        password = encrypt(password);
        user.setUserType(userType);
        user.setPassword(password);
        user.setName(name);
        user.setIDCard(IDCard);
        user.setPhone(phone);
        user.setSchool(school);
        user.setIntroduction(introduction);
        user.setAvatar(avatar);
        userService.updateUserByPk_user(user);
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
        User getUser=userService.login(user);
        Boolean isTrue = EncryptHelper.unencrypt(getUser.getPassword()).equals(user.getPassword());
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        result.put("result", isTrue);
        if (isTrue) {
            user.setPk_user(getUser.getPk_user());
            result.put("token",user);
        }
        return result.toJSONString();
    }
    // 用户登陆信息
    @ResponseBody
    @RequestMapping("userInfo")
    public String userInfo(@RequestBody JSONObject jsonParam) {
        User user=new User();
        int pk_user=(Integer)jsonParam.get("pk_user");
        user.setPk_user(pk_user);
        User getUser=userService.selectUserByPk_user(user);
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("roles", new String[]{getUser.getUserType()});
        data.put("avatar", getUser.getAvatar());
        data.put("introduction", getUser.getIntroduction());
        data.put("name", getUser.getName());
        data.put("userPk", -1);
        data.put("tenantName", -1);
        data.put("tenantPk", -1);
        data.put("defaultOrg", new String[0]);
        result.put("data",data);
        result.put("code",20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "logout", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String logOut() {

        JSONObject result = new JSONObject();
        result.put("code",20000);

        return result.toJSONString();
    }
}
