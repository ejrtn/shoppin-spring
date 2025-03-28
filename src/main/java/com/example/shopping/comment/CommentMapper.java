package com.example.shopping.comment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {

    boolean commentSave(CommentDto commentDto);

    boolean commentUpdate(CommentDto commentDto);

    List<CommentDto> commentList(CommentDto commentDto);

    boolean commentDelete(String commentId);
}
