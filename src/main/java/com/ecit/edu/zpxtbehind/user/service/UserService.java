package com.ecit.edu.zpxtbehind.user.service;


import com.ecit.edu.zpxtbehind.user.bean.User;
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
    public void updateUserByPk_user(User user) {
        userMapper.updateUserByPk_user(user);
    }
    public List<User> selectUserByExample(User user) {
        return userMapper.selectUserByExample(user);
    }
    public User selectUserByPk_user(User user) {
        return userMapper.selectUserByPk_user(user);
    }
    public User selectUserByName(User user) {
        return userMapper.selectUserByName(user);
    }
    public Boolean checkName(String name){
        return userMapper.checkName(name) == null;
    }

    public void deleteUser(Integer pk_user){
        userMapper.deleteUser(pk_user);
    }
}
