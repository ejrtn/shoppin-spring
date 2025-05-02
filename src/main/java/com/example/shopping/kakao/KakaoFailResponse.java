package com.example.shopping.kakao;

import lombok.Data;

@Data
public class KakaoFailResponse{
    private String error_code;
    private String error_message;
    private String extras;
}
