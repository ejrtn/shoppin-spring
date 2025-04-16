package com.example.shopping.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("userSave")
    public int userSave(UserDto userDto){
        return userService.userSave(userDto);
    }

    @PostMapping("userDelete")
    public int userDelete(String userId){
        return userService.userDelete(userId);
    }

    @PostMapping("login")
    public Map<String,String> login(String userId, String password){
        return userService.login(userId,password);
    }

    @PostMapping("passwordUpdate")
    public int passwordUpdate(String userId, String password, String newPassword){
        return userService.passwordUpdate(userId,password,newPassword);
    }

    @PostMapping("findId")
    public int findId(String name, String email){
        return userService.findId(name,email);
    }

    @PostMapping("findPassword")
    public int findPassword(String name, String email, String userId){
        return userService.findPassword(name,email,userId);
    }
}
