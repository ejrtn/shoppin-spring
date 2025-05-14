package com.example.shopping.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/userSave")
    @ResponseBody
    public int userSave(UserDto userDto){
        return userService.userSave(userDto);
    }

    @PostMapping("/userDelete")
    public int userDelete(String userId){
        return userService.userDelete(userId);
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public int login(HttpSession httpSession, String userId, String password){

        int r = userService.login(userId,password);
        if(r == 1) httpSession.setAttribute("userId",userId);
        return r;
    }

    @PostMapping("/logout")
    @ResponseBody
    public int logout(HttpSession httpSession){
        httpSession.removeAttribute("userId");
        return 1;
    }

    @PostMapping("/passwordUpdate")
    public int passwordUpdate(String userId, String password, String newPassword){
        return userService.passwordUpdate(userId,password,newPassword);
    }

    @PostMapping("/findId")
    @ResponseBody
    public String findId(String name, String email){
        return userService.findId(name,email);
    }

    @PostMapping("/findPassword")
    @ResponseBody
    public int findPassword(String name, String email, String userId){

        return userService.findPassword(name,email,userId);
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/idDoubleCheck")
    @ResponseBody
    public int idDoubleCheck(String userId){
        return userService.idDoubleCheck(userId);
    }

    @GetMapping("/idpw")
    public String findIdPw(){
        return "findIdPw";
    }

    @GetMapping("/userInfo")
    public String userInfo(){
        return "userInfo";
    }

    @PostMapping("/getUser")
    @ResponseBody
    public UserDto getUser(@RequestParam String userId){
        return userService.getUser(userId);
    }

    @PostMapping("/userUpdate")
    @ResponseBody
    public int userUpdate(UserDto userDto){
        return userService.userUpdate(userDto);
    }
}
