package com.example.shopping.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    CourierCompanyService courierCompanyService;

    @GetMapping("delivery")
    public String delivery(){
        return "delivery";
    }

    @PostMapping("deliverySave")
    public int deliverySave(DeliveryDto deliveryDto){
        return deliveryService.deliverySave(deliveryDto);
    }

    @PostMapping("deliveryList")
    @ResponseBody
    public List<DeliveryDto> deliveryList(@RequestParam String userId, @RequestParam int start){
        return deliveryService.deliveryList(userId,start);
    }

    @PostMapping("deliveryStatusUpdate")
    @ResponseBody
    public int deliveryStatusUpdate(@RequestParam String tid,@RequestParam String status){
        return deliveryService.deliveryStatusUpdate(tid,status);
    }

    @PostMapping("getDelivery")
    @ResponseBody
    public DeliveryDto getDelivery(@RequestParam String deliveryId){
        return deliveryService.getDelivery(deliveryId);
    }

    // 스마트택배 API
    // https://tracking.sweettracker.co.kr:8443/templates/app.html#/
    @PostMapping("courierCompanyList")
    public String courierCompanyList() {
        return courierCompanyService.courierCompanyList();
    }

    @PostMapping("/searchWaybill")
    @ResponseBody
    public String searchWaybill(String trackNumber, String companyName) throws UnsupportedEncodingException {
        return courierCompanyService.searchWaybill(trackNumber,companyName);
    }

    @PostMapping("deliveryAddressSave")
    @ResponseBody
    public int deliveryAddressSave(DeliveryAddressListDto deliveryAddressListDto){
        return deliveryService.deliveryAddressSave(deliveryAddressListDto);
    }

    @PostMapping("deliveryAddressChange")
    @ResponseBody
    public int deliveryAddressChange(DeliveryAddressListDto deliveryAddressListDto){
        return deliveryService.deliveryAddressChange(deliveryAddressListDto);
    }

    @PostMapping("deliveryAddressList")
    @ResponseBody
    public List<DeliveryAddressListDto> deliveryAddressList(String userId){
        return deliveryService.deliveryAddressList(userId);
    }

    @PostMapping("deliveryAddressDefault")
    @ResponseBody
    public DeliveryAddressListDto deliveryAddressDefault(String userId){
        return deliveryService.deliveryAddressDefault(userId);
    }

    @PostMapping("deliveryAddressOne")
    @ResponseBody
    public DeliveryAddressListDto deliveryAddressOne(String deliveryAddressId){
        return deliveryService.deliveryAddressOne(deliveryAddressId);
    }

    @PostMapping("deliveryAddressDelete")
    @ResponseBody
    public int deliveryAddressDelete(String deliveryAddressId){
        return deliveryService.deliveryAddressDelete(deliveryAddressId);
    }

    @GetMapping("deliveryAddress")
    public String deliveryAddress(){
        return "deliveryAddress";
    }

    @GetMapping("deliveryAddressSC")
    public String deliveryAddressSC(Model model,@RequestParam(required = false, value = "deliveryAddressId") String deliveryAddressId){

        model.addAttribute("deliveryAddressId",deliveryAddressId);
        return "deliveryAddressSC";
    }

    @GetMapping("detail")
    public String detail(@RequestParam String deliveryId){
        return "detail";
    }

    @PostMapping("deliveryDetailList")
    @ResponseBody
    public List<DeliveryDetailDto> deliveryDetailList(@RequestParam String deliveryId){
        return deliveryService.deliveryDetailList(deliveryId);
    }

    @PostMapping("deliveryDetailCommentsList")
    @ResponseBody
    public List<DeliveryDetailDto> deliveryDetailList(@RequestParam String deliveryId,@RequestParam String userId){
        return deliveryService.deliveryDetailCommentsList(deliveryId,userId);
    }

    @PostMapping("/courier")
    public String courier(Model model, @RequestParam String t_invoice, @RequestParam String t_name){
        model.addAttribute("trackNumber",t_invoice);
        model.addAttribute("companyName",t_name);
        return "courier";
    }
}
