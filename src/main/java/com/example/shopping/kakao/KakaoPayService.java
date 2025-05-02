package com.example.shopping.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class KakaoPayService {

    @Value("${kakao.cid}")
    String cid;

    @Value("${kakao.secretKey}")
    String admin_Key;

    private String tid;

    private KakaoReadyResponse readyResponse;

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "DEV_SECRET_KEY " + admin_Key);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public KakaoReadyResponse kakaoPayReady(KakaoReadyReqDto kakaoReadyReqDto) {

        kakaoReadyReqDto.setCid(cid);
        kakaoReadyReqDto.setApproval_url("http://localhost:8080/kakaoPayment/approve");
        kakaoReadyReqDto.setCancel_url("http://localhost:8080/kakaoPayment/cancel");
        kakaoReadyReqDto.setFail_url("http://localhost:8080/kakaoPayment/fail");

        //파라미터, 헤더
        HttpEntity<KakaoReadyReqDto> requestEntity = new HttpEntity<>(kakaoReadyReqDto, this.getHeaders());

        //카카오에게 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        //api 요청
        readyResponse = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/ready",  	//url
                requestEntity,                              //본문
                KakaoReadyResponse.class                    //응답 클래스
        );

        // 주문번호와 TID를 매핑해서 저장해놓는다.
        // Mapping TID with partner_order_id then save it to use for approval request.
        this.tid = readyResponse.getTid();

        return readyResponse;
    }

    public KakaoApproveResponse ApproveResponse(String pgToken){
        KakaoApproveReqDto kakaoApproveReqDto = new KakaoApproveReqDto();
        kakaoApproveReqDto.setCid(cid);
        kakaoApproveReqDto.setTid(tid);
        kakaoApproveReqDto.setPartner_user_id("1");
        kakaoApproveReqDto.setPartner_order_id("1");
        kakaoApproveReqDto.setPg_token(pgToken);

        HttpEntity<KakaoApproveReqDto> requestEntity = new HttpEntity<>(kakaoApproveReqDto, this.getHeaders());

        //외부에 보낼 url
        RestTemplate resetTemplate = new RestTemplate();

        //api 요청
        KakaoApproveResponse approveResponse = resetTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/approve",
                requestEntity,
                KakaoApproveResponse.class
        );

        return approveResponse;
    }

    public KakaoCancelResponse kakaoPayCancel(){

        KakaoCancleReqDto kakaoCancleReqDto = new KakaoCancleReqDto();
        kakaoCancleReqDto.setCid(cid);
        kakaoCancleReqDto.setTid(tid);
        kakaoCancleReqDto.setCancel_amount(1000);
        kakaoCancleReqDto.setCancel_tax_free_amount(0);

        HttpEntity<KakaoCancleReqDto> requestEntity = new HttpEntity<>(kakaoCancleReqDto, this.getHeaders());

        //외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoCancelResponse cancelResponse = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/cancel",
                requestEntity,
                KakaoCancelResponse.class
        );

        return cancelResponse;
    }


}
