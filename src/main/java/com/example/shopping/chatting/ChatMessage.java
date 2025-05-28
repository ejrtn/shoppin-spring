package com.example.shopping.chatting;

import lombok.Data;

@Data
public class ChatMessage {
    private String roomId;
    private String message;
    private String sender;
    private MessageType type; // 메시지 타입
    // 메시지 타입 : 입장, 채팅, 나감
    public enum MessageType {
        JOIN, TALK, QUIT
    }
}
