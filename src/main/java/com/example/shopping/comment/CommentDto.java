package com.example.shopping.comment;

import lombok.Data;

@Data
public class CommentDto {

    private int commentId;
    private int rating;
    private String content;
    private String title;
    private String productId;
    private String cdate;
    private String userId;
}
