package com.example.shopping.kakao;

import lombok.Data;

@Data
public class KakaoCancleReqDto {
    private String cid;
    private String tid;                 //결제 고유번호, 20자
    private int cancel_amount;          //취소 금액
    private int cancel_tax_free_amount; //취소 비과세 금액
}
