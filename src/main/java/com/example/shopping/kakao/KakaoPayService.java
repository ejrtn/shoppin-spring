package com.example.shopping.kakao;

import com.example.shopping.delivery.DeliveryDto;
import com.example.shopping.delivery.DeliveryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class KakaoPayService {

    @Value("${kakao.cid}")
    String cid;

    @Value("${kakao.secretKey}")
    String admin_Key;

    @Autowired
    private DeliveryService deliveryService;

    private String tid;

    private KakaoReadyResponse readyResponse;

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "DEV_SECRET_KEY " + admin_Key);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public KakaoReadyResponse kakaoPayReady(DeliveryDto deliveryDto,String addr) {
        KakaoReadyReqDto kakaoReadyReqDto = new KakaoReadyReqDto();
        kakaoReadyReqDto.setCid(cid);
        kakaoReadyReqDto.setPartner_order_id("1");
        String partner_user_id = "1";
        kakaoReadyReqDto.setPartner_user_id(partner_user_id);
        kakaoReadyReqDto.setItem_name("DEOKSU "+(deliveryDto.getDeliveryDetailDtos().size()-1)+"개 제품 구매");
        kakaoReadyReqDto.setQuantity(deliveryDto.getQuantity());
        kakaoReadyReqDto.setTotal_amount(deliveryDto.getTotalAmount());
        kakaoReadyReqDto.setTax_free_amount(0);
        kakaoReadyReqDto.setApproval_url("http://"+addr+"/kakaoPayment/approve?");
        kakaoReadyReqDto.setCancel_url("http://"+addr+"/kakaoPayment/cancel");
        kakaoReadyReqDto.setFail_url("http://"+addr+"/kakaoPayment/fail");

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
        if(deliveryDto.getDeliveryId() != 0){
            deliveryService.deliveryRepay(deliveryDto.getTid(),tid);
        }else {
            deliveryDto.setTid(readyResponse.getTid());
            deliveryService.deliverySave(deliveryDto);
        }
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

    public KakaoCancelResponse kakaoPayCancel(String tid,int amount){

        KakaoCancleReqDto kakaoCancleReqDto = new KakaoCancleReqDto();
        kakaoCancleReqDto.setCid(cid);
        kakaoCancleReqDto.setTid(tid);
        kakaoCancleReqDto.setCancel_amount(amount);
        kakaoCancleReqDto.setCancel_tax_free_amount(0);

        HttpEntity<KakaoCancleReqDto> requestEntity = new HttpEntity<>(kakaoCancleReqDto, this.getHeaders());

        //외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoCancelResponse cancelResponse = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/cancel",
                requestEntity,
                KakaoCancelResponse.class
        );

        deliveryService.deliveryStatusUpdate(tid,"결제취소");
        
        return cancelResponse;
    }


}
