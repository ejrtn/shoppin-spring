package com.example.shopping.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

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
    public List<DeliveryDto> deliveryList(@RequestParam String userId){
        return deliveryService.deliveryList(userId);
    }

    @PostMapping("deliveryStatusUpdate")
    @ResponseBody
    public int deliveryStatusUpdate(@RequestParam String tid,@RequestParam String status){
        return deliveryService.deliveryStatusUpdate(tid,status);
    }

    @PostMapping("delivery")
    public DeliveryDto delivery(String deliveryId){
        return deliveryService.delivery(deliveryId);
    }

    // 스마트택배 API
    // https://tracking.sweettracker.co.kr:8443/templates/app.html#/
    @PostMapping("courierCompanyList")
    public String courierCompanyList() {
        return deliveryService.courierCompanyList();
    }

    @PostMapping("searchWaybill")
    public String searchWaybill(String date,String trackNumber,String courierCode){
        return deliveryService.searchWaybill(date,trackNumber,courierCode);
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

    @PostMapping("deliveryDetailList")
    @ResponseBody
    public List<DeliveryDetailDto> deliveryDetailList(@RequestParam String deliveryId){
        return deliveryService.deliveryDetailList(deliveryId);
    }
}
