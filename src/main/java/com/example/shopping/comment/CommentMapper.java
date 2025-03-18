package com.example.shopping.comment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {

    boolean commentSave(@Param("comment") CommentDto commentDto);

    boolean commentUpdate(@Param("comment") CommentDto commentDto);

    List<Map<String,String>> commentList(@Param("comment") CommentDto commentDto);


}
