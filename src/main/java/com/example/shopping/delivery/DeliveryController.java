package com.example.shopping.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Controller
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

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
}
