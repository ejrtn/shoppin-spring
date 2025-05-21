package com.example.shopping.comment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    int commentSave(CommentDto commentDto);

    int commentUpdate(CommentDto commentDto);

    int commentDelete(String commentId);

    List<CommentDto> productReview(String productId, int reviewStartNum);
}
