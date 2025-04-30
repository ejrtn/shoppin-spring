package com.example.shopping.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    DeliveryMapper deliveryMapper;

    public int deliverySave(DeliveryDto deliveryDto){
        return deliveryMapper.deliverySave(deliveryDto);
    }

    public List<DeliveryDto> deliveryList(DeliveryDto deliveryDto){
        return deliveryMapper.deliveryList(deliveryDto);
    }

    public int deliveryUpdate(DeliveryDto deliveryDto){
        return deliveryMapper.deliveryUpdate(deliveryDto);
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

    public int deliveryAddressSave(deliveryAddressListDto deliveryAddressListDto) {
        System.out.println(deliveryAddressListDto.toString());
        if(deliveryAddressListDto.getDefaultYn().equals("Y")){
            deliveryMapper.deliveryAddressChangeDefaultYn(deliveryAddressListDto.getUserId());
        }
        return deliveryMapper.deliveryAddressSave(deliveryAddressListDto);
    }

    public int deliveryAddressChange(deliveryAddressListDto deliveryAddressListDto) {
        if(deliveryAddressListDto.getDefaultYn().equals("Y")){
            deliveryMapper.deliveryAddressChangeDefaultYn(deliveryAddressListDto.getUserId());
        }
        System.out.println(deliveryAddressListDto.toString());
        return deliveryMapper.deliveryAddressChange(deliveryAddressListDto);
    }

    public List<deliveryAddressListDto> deliveryAddressList(String userId) {
        return deliveryMapper.deliveryAddressList(userId);
    }

    public deliveryAddressListDto deliveryAddressDefault(String userId){
        return deliveryMapper.deliveryAddressDefault(userId);
    }

    public int deliveryAddressDelete(String deliveryAddressId) {
        return deliveryMapper.deliveryAddressDelete(deliveryAddressId);
    }

    public deliveryAddressListDto deliveryAddressOne(String deliveryAddressId) {
        return deliveryMapper.deliveryAddressOne(deliveryAddressId);
    }
}
