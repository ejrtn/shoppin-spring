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
    public List<DeliveryDto> deliveryList(DeliveryDto deliveryDto){
        return deliveryService.deliveryList(deliveryDto);
    }

    @PostMapping("deliveryUpdate")
    public int deliveryUpdate(DeliveryDto deliveryDto){
        return deliveryService.deliveryUpdate(deliveryDto);
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
    public int deliveryAddressSave(deliveryAddressListDto deliveryAddressListDto){
        return deliveryService.deliveryAddressSave(deliveryAddressListDto);
    }

    @PostMapping("deliveryAddressChange")
    @ResponseBody
    public int deliveryAddressChange(deliveryAddressListDto deliveryAddressListDto){
        return deliveryService.deliveryAddressChange(deliveryAddressListDto);
    }

    @PostMapping("deliveryAddressList")
    @ResponseBody
    public List<deliveryAddressListDto> deliveryAddressList(String userId){
        return deliveryService.deliveryAddressList(userId);
    }

    @PostMapping("deliveryAddressDefault")
    @ResponseBody
    public deliveryAddressListDto deliveryAddressDefault(String userId){
        return deliveryService.deliveryAddressDefault(userId);
    }

    @PostMapping("deliveryAddressOne")
    @ResponseBody
    public deliveryAddressListDto deliveryAddressOne(String deliveryAddressId){
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
}
