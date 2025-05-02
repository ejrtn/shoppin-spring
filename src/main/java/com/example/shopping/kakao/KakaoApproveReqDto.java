package com.example.shopping.kakao;

import lombok.Data;

@Data
public class KakaoApproveReqDto {
    private String cid;
    private String tid;                 //결제 고유번호, 결제 준비 API 응답에 포함
    private String partner_order_id;    //가맹점 주문번호, 결제 준비 API 요청과 일치해야 함
    private String partner_user_id;     //가맹점 회원 id, 결제 준비 API 요청과 일치해야 함

    //결제승인 요청을 인증하는 토큰
    //사용자 결제 수단 선택 완료 시, approval_url로 redirection 해줄 때 pg_token을 query string으로 전달
    private String pg_token;
}
