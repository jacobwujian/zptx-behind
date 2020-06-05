package com.ecit.edu.zpxtbehind.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.ecit.edu.zpxtbehind.HeaderParamUtil;
import com.ecit.edu.zpxtbehind.user.EncryptHelper;
import com.ecit.edu.zpxtbehind.user.bean.User;
import com.ecit.edu.zpxtbehind.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.ecit.edu.zpxtbehind.user.EncryptHelper.encrypt;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    HttpServletRequest request;
    private Integer getPk_user() {
        return HeaderParamUtil.getPKUser(request);
    }
    // 获取所有用户信息
    @ResponseBody
    @RequestMapping("getAllUsers")
    public String getAllUsers() throws Exception {
        List<User> users = userService.getAllUsers();
        List<User> newUsers = new ArrayList<>();
        for (User user : users) {
            String pa = EncryptHelper.unencrypt(user.getPassword()).substring(user.getUserName().length());
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
    public String insertUser(@RequestBody JSONObject jsonParam) throws Exception {
        String username = (String) jsonParam.get("userName");
        String password = username;
        if (jsonParam.get("password") != null){
            password  +=   (String) jsonParam.get("password");
        }else {
            password  +=   (String) jsonParam.get("password1");

        }
        String name = (String) jsonParam.get("name");
        String userType = (String) jsonParam.get("userType");
        String phone = (String) jsonParam.get("phone");
        User user = new User();
        user.setUserName(username);
        password = encrypt(password);
        user.setUserType(userType);
        user.setPassword(password);
        user.setName(name);
        user.setPhone(phone);
        userService.insertUser(user);
        user.setPassword((String) jsonParam.get("password"));
        if(userType.equals("user")) {
            restTemplate.postForObject("http://127.0.0.1:8123/behind/api/resume/insertResume", user, JSONObject.class);
        }
        if(userType.equals("editor")) {
            restTemplate.postForObject("http://127.0.0.1:8123/behind/api/company/insertCompany", user, JSONObject.class);
        }
        JSONObject result = new JSONObject();
        result.put("user", user);
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 修改用户信息
    @ResponseBody
    @RequestMapping("updateUserByPk_user")
    public String updateUserByPk_user(@RequestBody JSONObject jsonParam) throws Exception {
        int pk_user = (Integer) jsonParam.get("pk_user");
        User user = new User();
        user.setPk_user(pk_user);
        String username;
        String password;
        if(jsonParam.get("userName")!=null) {
            username = (String) jsonParam.get("userName");
            user.setUserName(username);
            if (jsonParam.get("password") != null) {
                password = jsonParam.get("userName") + (String) jsonParam.get("password");
                password = encrypt(password);
                user.setPassword(password);
            }
        }
        String name;
        if ( jsonParam.get("name")!= null){
            name = (String) jsonParam.get("name");
            user.setName(name);
        }
        String userType ;
        if (jsonParam.get("userType")!=null){
            userType = (String) jsonParam.get("userType");
            user.setUserType(userType);
        }
        String introduction;
        if (jsonParam.get("introduction")!=null){
            introduction = (String) jsonParam.get("introduction");
            user.setIntroduction(introduction);
        }
        String avatar;
        if (jsonParam.get("avatar")!=null){
            avatar = (String) jsonParam.get("avatar");
            user.setAvatar(avatar);
        }
        String IDCard;
        if (jsonParam.get("IDCard")!=null){
            IDCard = (String) jsonParam.get("IDCard");
            user.setIDCard(IDCard);
        }
        String phone;
        if (jsonParam.get("phone")!=null){
            phone = (String) jsonParam.get("phone");
            user.setPhone(phone);
        }
        String school;
        if (jsonParam.get("school")!=null){
            school = (String) jsonParam.get("school");
            user.setSchool(school);
        }

        userService.updateUserByPk_user(user);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 修改用户密码
    @ResponseBody
    @RequestMapping("updateUserPassword")
    public String updateUserPassword(@RequestBody JSONObject jsonParam) throws Exception {
        Integer pk_user = getPk_user();
        User user = new User();
        user.setPk_user(pk_user);
        user =  userService.selectUserByPk_user(user);
        String oldPassword = EncryptHelper.unencrypt(user.getPassword());
        String getOldPassword =user.getUserName()+jsonParam.get("oldPwd");
        JSONObject result = new JSONObject();
        if (oldPassword.equals(getOldPassword)){
            String password = encrypt(user.getUserName()+jsonParam.get("newPwd"));
            user.setPassword(password);
            userService.updateUserPassword(user);
            result.put("code", 20000);
            result.put("message", "success");
        }
        else {
            result.put("code", 20001);
            result.put("message", "旧密码不正确");
        }
        return result.toJSONString();
    }

    // 用户登陆校验
    @ResponseBody
    @RequestMapping("login")
    public String login(@RequestBody JSONObject jsonParam) throws Exception {
        User user = new User();
        String username = (String) jsonParam.get("username");
        user.setUserName(username);
        String password = (String) jsonParam.get("password");
        Base64.Decoder decoder = Base64.getDecoder();
        String pw = new String(decoder.decode(password));
        user.setPassword(username + pw);
        User getUser = userService.login(user);
        String reENC =  EncryptHelper.unencrypt(getUser.getPassword());
        boolean isTrue = reENC.equals(user.getPassword());
        JSONObject result = new JSONObject();
        if (isTrue) {
            result.put("code", 20000);
            result.put("message", "success");
            user.setPk_user(getUser.getPk_user());
            result.put("token", user);
        } else {
            result.put("code", 20001);
            result.put("message", "登陆失败，账号不存在或账号密码不一致");
        }
        return result.toJSONString();
    }

    // 用户登陆信息
    @ResponseBody
    @RequestMapping("userInfo")
    public String userInfo(@RequestBody JSONObject jsonParam) {
        User user = new User();
        int pk_user = (Integer) jsonParam.get("pk_user");
        user.setPk_user(pk_user);
        User getUser = userService.selectUserByPk_user(user);
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
        data.put("pk_user", getUser.getPk_user());
        result.put("data", data);
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 查询
    @ResponseBody
    @RequestMapping("selectUserByExample")
    public String selectUserByExample(@RequestBody JSONObject jsonParam) throws Exception {
        User user = new User();
        String userName = (String) jsonParam.get("searchName");
        if (userName != null && !userName.isEmpty()) {
            user.setUserName("%" + userName + "%");
        }
        String chooseRlue = (String) jsonParam.get("chooseRule");
        if (chooseRlue != null && !chooseRlue.isEmpty()) {
            user.setUserType(chooseRlue);
        }
        JSONObject result = new JSONObject();
        List<User> users = userService.selectUserByExample(user);
        List<User> newUsers = new ArrayList<>();
        for (User user1 : users) {
            String pa = EncryptHelper.unencrypt(user1.getPassword()).substring(user1.getUserName().length());
            user1.setPassword(pa);
            newUsers.add(user1);
        }
        result.put("data", newUsers);
        result.put("code", 20000);
        result.put("message", "success");
        return result.toJSONString();
    }

    // 退出登陆
    @ResponseBody
    @RequestMapping(value = "logout", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String logOut() {

        JSONObject result = new JSONObject();
        result.put("code", 20000);

        return result.toJSONString();
    }

    // 姓名查重
    @ResponseBody
    @RequestMapping(value = "checkName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String checkName(@RequestBody JSONObject jsonParam) {
        String name = (String) jsonParam.get("name");
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        result.put("data", userService.checkName(name));
        return result.toJSONString();
    }

    // 删除
    @ResponseBody
    @RequestMapping(value = "deleteUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteUser(@RequestBody JSONObject jsonParam) {
        Integer pk_user = (Integer) jsonParam.get("pk_user");
        userService.deleteUser(pk_user);
        restTemplate.postForObject("http://127.0.0.1:8123/behind/api/resume/deleteResume", jsonParam, JSONObject.class);
        restTemplate.postForObject("http://127.0.0.1:8123/behind/api/company/deleteCompany", jsonParam, JSONObject.class);
        restTemplate.postForObject("http://127.0.0.1:8123/behind/api/act/deleteActByUser", jsonParam, JSONObject.class);
        JSONObject result = new JSONObject();
        result.put("code", 20000);
        return result.toJSONString();
    }
}
