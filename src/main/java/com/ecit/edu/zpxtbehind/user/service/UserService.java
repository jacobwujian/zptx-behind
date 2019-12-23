package com.ecit.edu.zpxtbehind.user.service;


import com.ecit.edu.zpxtbehind.user.bean.User;
import com.ecit.edu.zpxtbehind.user.bean.UserInfo;
import com.ecit.edu.zpxtbehind.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.selectUser();
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public void updateUserPassword(User user) {
        userMapper.updateUserPassword(user);
    }

    public User login(User user) {
        return userMapper.selectUserByName(user);
    }

    public UserInfo selectUserInfoByPk_user(UserInfo userInfo) {
        return userMapper.selectUserInfoByPk_user(userInfo);
    }
    public void insertUserInfo(UserInfo userInfo) {
        userMapper.insertUserInfo(userInfo);
    }
    public void updateUserInfo(UserInfo userInfo) {
        userMapper.updateUserInfo(userInfo);
    }


}
