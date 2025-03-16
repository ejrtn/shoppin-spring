package com.example.shopping.user;

import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    public boolean userSave(UserDto userDto){
        return userService.userSave(userDto);
    }
}
