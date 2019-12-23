package com.ecit.edu.zpxtbehind.user.mapper;



import com.ecit.edu.zpxtbehind.user.bean.User;

import java.util.List;

public interface UserMapper {
    List<User> selectUser();
    void insertUser(User user);
    void updateUserPassword(User user);
    User selectUserByNameAndPassword(User user);
}
