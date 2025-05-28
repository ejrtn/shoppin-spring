package com.example.shopping.chatting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/chat")
    public String chatGet() {
        return "chat";
    }

    @PostMapping("/chatList")
    @ResponseBody
    public List<ChatDto> chatList(String userId){
        return chatService.chatList(userId);
    }


}
