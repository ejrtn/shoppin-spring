package com.example.shopping.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    boolean userSave(@Param("user") UserDto userDto);

    boolean userdelete(@Param("user") UserDto userDto);

    boolean login(@Param("userId") String userId, @Param("password") String password);

    boolean infoUpdate(@Param("product") UserDto userDto);

    String searchId(@Param("name") String name, @Param("email") String email);

    String searchPassword(@Param("name") String name, @Param("email") String email, @Param("userId") String userId);
}
