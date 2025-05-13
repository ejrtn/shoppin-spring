package com.example.shopping.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    DeliveryMapper deliveryMapper;

    public int deliverySave(DeliveryDto deliveryDto){
        int result = deliveryMapper.deliverySave(deliveryDto);
        if(result == 1) {
            for (int i = 0; i < deliveryDto.getDeliveryDetailDtos().size(); i++) {
                deliveryDto.getDeliveryDetailDtos().get(i).setDeliveryId(deliveryDto.getDeliveryId());
                deliveryMapper.deliveryDetailSave(deliveryDto.getDeliveryDetailDtos().get(i));
            }
        }
        return result;
    }

    public List<DeliveryDto> deliveryList(String userId){
        return deliveryMapper.deliveryList(userId);
    }

    public int deliveryStatusUpdate(String tid,String status){
        return deliveryMapper.deliveryStatusUpdate(tid,status);
    }
    public int deliveryRepay(String tid,String newTid){
        return deliveryMapper.deliveryRepay(tid,newTid);
    }

    public DeliveryDto delivery(String deliveryId){
        return deliveryMapper.delivery(deliveryId);
    }

    // 스마트택배 API
    // https://tracking.sweettracker.co.kr:8443/templates/app.html#/
    public String courierCompanyList() {

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://info.sweettracker.co.kr/api/v1/companylist?t_key=pwdhGbqe5vbLNd9WE9TuyA"))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response code and content
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            return response.body();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public String searchWaybill(String date,String trackNumber,String courierCode){
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://info.sweettracker.co.kr/api/v1/trackingInfo?t_key=pwdhGbqe5vbLNd9WE9TuyA&t_invoice="+trackNumber+"&t_code="+courierCode))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response code and content
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            return response.body();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public int deliveryAddressSave(DeliveryAddressListDto deliveryAddressListDto) {

        if(deliveryAddressListDto.getDefaultYn().equals("Y")){
            deliveryMapper.deliveryAddressChangeDefaultYn(deliveryAddressListDto.getUserId());
        }

        return deliveryMapper.deliveryAddressSave(deliveryAddressListDto);
    }

    public int deliveryAddressChange(DeliveryAddressListDto deliveryAddressListDto) {
        if(deliveryAddressListDto.getDefaultYn().equals("Y")){
            deliveryMapper.deliveryAddressChangeDefaultYn(deliveryAddressListDto.getUserId());
        }
        System.out.println(deliveryAddressListDto.toString());
        return deliveryMapper.deliveryAddressChange(deliveryAddressListDto);
    }

    public List<DeliveryAddressListDto> deliveryAddressList(String userId) {
        return deliveryMapper.deliveryAddressList(userId);
    }

    public DeliveryAddressListDto deliveryAddressDefault(String userId){
        return deliveryMapper.deliveryAddressDefault(userId);
    }

    public int deliveryAddressDelete(String deliveryAddressId) {
        return deliveryMapper.deliveryAddressDelete(deliveryAddressId);
    }

    public DeliveryAddressListDto deliveryAddressOne(String deliveryAddressId) {
        return deliveryMapper.deliveryAddressOne(deliveryAddressId);
    }

    public List<DeliveryDetailDto> deliveryDetailList(String deliveryId){
        return deliveryMapper.deliveryDetailList(deliveryId);
    }
}
