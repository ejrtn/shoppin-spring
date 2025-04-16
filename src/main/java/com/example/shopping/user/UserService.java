package com.example.shopping.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService{

    @Autowired
    UserMapper userMapper;

    public int userSave(UserDto userDto){
        return userMapper.userSave(userDto);
    }

    public int userDelete(String userId){
        return userMapper.userDelete(userId);
    }

    public Map<String,String> login(String userId, String password){
        return userMapper.login(userId,password);
    }

    public int passwordUpdate(String userId, String password, String newPassword){
        return userMapper.passwordUpdate(userId,password,newPassword);
    }

    public int findId(String name, String email){
        return userMapper.findId(name,email);
    }

    public int findPassword(String name, String email, String userId){
        return userMapper.findPassword(name,email,userId);
    }

}
