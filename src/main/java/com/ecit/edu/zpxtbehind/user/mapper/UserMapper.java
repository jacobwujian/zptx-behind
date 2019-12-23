package com.ecit.edu.zpxtbehind.user.mapper;



import com.ecit.edu.zpxtbehind.user.bean.User;
import com.ecit.edu.zpxtbehind.user.bean.UserInfo;

import java.util.List;

public interface UserMapper {
    List<User> selectUser();
    void insertUser(User user);
    void updateUserPassword(User user);
    User selectUserByName(User user);

    UserInfo selectUserInfoByPk_user(UserInfo userInfo);
    void insertUserInfo(UserInfo userInfo);
    void updateUserInfo(UserInfo userInfo);
}
