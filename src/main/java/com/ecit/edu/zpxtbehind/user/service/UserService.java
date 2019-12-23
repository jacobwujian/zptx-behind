package com.ecit.edu.zpxtbehind.user.service;


import com.ecit.edu.zpxtbehind.user.EncryptHelper;
import com.ecit.edu.zpxtbehind.user.bean.User;
import com.ecit.edu.zpxtbehind.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.selectUser();
    }

    public void insertUser(User user){userMapper.insertUser(user);}
    public void updateUserPassword(User user){userMapper.updateUserPassword(user);}
    public Boolean login(User user) throws NoSuchPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        User getUser=userMapper.selectUserByNameAndPassword(user);
        String pas=EncryptHelper.unencrypt(getUser.getPassword());
        return pas.equals(user.getPassword());
    }
}
