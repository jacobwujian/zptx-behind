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
}
