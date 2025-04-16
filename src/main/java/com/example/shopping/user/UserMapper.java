package com.example.shopping.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UserMapper {

    int userSave(UserDto userDto);

    int userDelete(String userId);

    Map<String,String> login(String userId, String password);

    int passwordUpdate(String userId, String password, String newPassword);

    int findId(String name, String email);

    int findPassword(String name, String email, String userId);
}
