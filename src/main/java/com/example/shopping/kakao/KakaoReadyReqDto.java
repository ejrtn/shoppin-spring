package com.example.shopping.kakao;

import lombok.Data;

@Data
public class KakaoReadyReqDto {

    private String cid;
    private String partner_order_id;    //가맹점 주문번호, 최대 100자
    private String partner_user_id;     //가맹점 회원 id, 최대 100자(실명, ID와 같은 개인정보가 포함되지 않도록 유의)
    private String item_name;           //상품명, 최대 100자
    private int quantity;               //상품 수량
    private int total_amount;           //상품 총액
    private int tax_free_amount;        //상품 비과세 금액
    private String approval_url;        //결제 성공 시 redirect url, 최대 255자
    private String cancel_url;          //결제 취소 시 redirect url, 최대 255자
    private String fail_url;            //결제 실패 시 redirect url, 최대 255자
}
