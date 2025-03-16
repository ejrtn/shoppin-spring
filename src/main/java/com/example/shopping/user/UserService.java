package com.example.shopping.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public boolean userSave(UserDto userDto){
        return true;
    }

    public boolean userdelete(UserDto userDto){
        return true;
    }

    public boolean login(String userId, String password){
        return true;
    }

    public boolean infoUpdate(UserDto userDto){
        return true;
    }

    public String searchId(String name, String email){
        return "searchId";
    }

    public String searchPassword(String name, String email, String userId){
        return "searchPassword";
    }
}
