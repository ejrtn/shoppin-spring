package com.example.shopping.kakao;

import lombok.Data;

@Data
public class KakaoReadyResponse {
    private String tid;                     	//결제 고유번호
    private String next_redirect_app_url;       //클라이언트가 앱일 경우 결제 페이지 url
    private String next_redirect_mobile_url;    //클라이언트가 모바일 경우 결제 페이지 url
    private String next_redirect_pc_url;     	//클라이언트가 웹일 경우 결제 페이지 url
    private String android_app_scheme;          //카카오페이 결제 화면으로 이동하는 Android 앱 스킴(Scheme) - 내부 서비스용
    private String ios_app_scheme;              //카카오페이 결제 화면으로 이동하는 iOS 앱 스킴 - 내부 서비스용
    private String created_at;             	//결제 준비 요청 시간
}
