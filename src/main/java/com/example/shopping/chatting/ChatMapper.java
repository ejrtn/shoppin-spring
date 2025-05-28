package com.example.shopping.chatting;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {

    int chatSave(ChatDto chatDto);

    List<ChatDto> chatList(String tableName);
}
