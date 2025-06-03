package com.example.shopping.kakao;

import com.example.shopping.comment.CommentService;
import com.example.shopping.common.CommonService;
import com.example.shopping.delivery.DeliveryDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;

@Controller
@RequestMapping("/kakaoPayment")
@RequiredArgsConstructor
public class KakaoPayController {

    @Autowired
    private KakaoPayService kakaoPayService;

    @Autowired
    private CommonService commonService;

    @PostMapping("/ready")  //결제준비요청
    @ResponseBody
    public KakaoReadyResponse readyToKakaoPay(HttpServletRequest request, @RequestBody DeliveryDto deliveryDto) {
        String ip = commonService.getClientIP(request);
        return kakaoPayService.kakaoPayReady(deliveryDto,ip+":"+request.getServerPort());
    }

    @GetMapping("/approve")
    public String approve(@RequestParam("pg_token") String pgToken, Model model) {
        KakaoApproveResponse approveResponse = kakaoPayService.ApproveResponse(pgToken);
        model.addAttribute("response", approveResponse);
        return "approve";
    }


    @GetMapping("/cancel")
    public String cancel() {
        // 주문건이 진짜 취소되었는지 확인 후 취소 처리
        // 결제내역조회(/v1/payment/status) api에서 status를 확인한다.
        // To prevent the unwanted request cancellation caused by attack,
        // the “show payment status” API is called and then check if the status is QUIT_PAYMENT before suspending the payment
        return "cancel";
    }

    @GetMapping("/fail")
    public String fail() {
        // 주문건이 진짜 실패되었는지 확인 후 실패 처리
        // 결제내역조회(/v1/payment/status) api에서 status를 확인한다.
        // To prevent the unwanted request cancellation caused by attack,
        // the “show payment status” API is called and then check if the status is FAIL_PAYMENT before suspending the payment
        return "/fail";
    }

    @PostMapping("/refund") //결제취소요청
    @ResponseBody
    public KakaoCancelResponse cancelPayRequest(@RequestParam String tid,@RequestParam int amount){
        return kakaoPayService.kakaoPayCancel(tid, amount);
    }

}
