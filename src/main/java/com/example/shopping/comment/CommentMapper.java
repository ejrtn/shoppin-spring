package com.example.shopping.comment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {

    int commentSave(CommentDto commentDto);

    int commentUpdate(CommentDto commentDto);

    List<CommentDto> commentList(CommentDto commentDto);

    int commentDelete(String commentId);
}
