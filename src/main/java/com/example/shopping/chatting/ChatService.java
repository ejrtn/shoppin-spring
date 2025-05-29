package com.example.shopping.chatting;

import com.example.shopping.user.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatService extends TextWebSocketHandler {

    private final ChatMapper chatMapper;

    private final UserMapper userMapper;

    private final ObjectMapper objectMapper;

    // 소켓 세션을 저장할 Set
    private final Set<WebSocketSession> sessions = new HashSet<>();

    // 채팅방 id와 소켓 세션을 저장할 Map
    private final Map<String,Set<WebSocketSession>> chatRoomSessionMap = new HashMap<>();


    // 소켓 연결 확인
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // TODO Auto-generated method stub
        sessions.add(session);
        session.sendMessage(new TextMessage("{\"message\":\"안녕하세요.<br>DEOKSU 쇼핑몰입니다.\",\"type\":\"HELLO\"}"));

    }

    // 소켓 메세지 처리
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);

        if (chatMessage.getType().equals(ChatMessage.MessageType.JOIN)) {
            chatRoomSessionMap.computeIfAbsent(chatMessage.getRoomId(), s -> new HashSet<>()).add(session);
            chatMessage.setMessage(userMapper.chatInfo(chatMessage.getRoomId()) + "님");
        } else if (chatMessage.getType().equals(ChatMessage.MessageType.QUIT)) {
            chatRoomSessionMap.get(chatMessage.getRoomId()).remove(session);
            chatMessage.setMessage(userMapper.chatInfo(chatMessage.getRoomId()) + "님");
        }else{
            ChatDto chatDto = new ChatDto();
            chatDto.setMessage(chatMessage.getMessage());
            chatDto.setSender(chatMessage.getSender());
            chatDto.setTableName("chat_"+chatMessage.getRoomId()+"_history");
            chatMapper.chatSave(chatDto);
        }
        if(chatRoomSessionMap.get(chatMessage.getRoomId())!=null) {
            for (WebSocketSession s : chatRoomSessionMap.get(chatMessage.getRoomId())) {
                if(s.isOpen()) s.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatMessage)));
            }
        }
        if(!chatMessage.getRoomId().equals("admin")) {
            for (WebSocketSession s : chatRoomSessionMap.get("admin")) {
                if(s.isOpen()) s.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatMessage)));
            }
        }
    }

    // 소켓 연결 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // TODO Auto-generated method stub
        sessions.remove(session);
    }

    public void chatSave(ChatDto chatDto){
        chatMapper.chatSave(chatDto);
    }

    public List<ChatDto> chatList(String userId){
        return chatMapper.chatList("chat_"+userId+"_history");
    }
}
