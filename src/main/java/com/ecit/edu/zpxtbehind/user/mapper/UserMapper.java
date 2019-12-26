package com.ecit.edu.zpxtbehind.user.mapper;



import com.ecit.edu.zpxtbehind.user.bean.User;

import java.util.List;

public interface UserMapper {
    List<User> selectUser();
    void insertUser(User user);
    void updateUserPassword(User user);
    User selectUserByName(User user);
    User selectUserByPk_user(User user);
    void updateUserByPk_user(User user);
    List<User> selectUserByExample(User user);
}
