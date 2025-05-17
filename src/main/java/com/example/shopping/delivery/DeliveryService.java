package com.example.shopping.delivery;

import com.example.shopping.kakao.KakaoCancelResponse;
import com.example.shopping.kakao.KakaoCancleReqDto;
import lombok.Data;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public List<DeliveryDto> deliveryList(String userId,int start){
        return deliveryMapper.deliveryList(userId,start);
    }

    public int deliveryStatusUpdate(String tid,String status){
        return deliveryMapper.deliveryStatusUpdate(tid,status);
    }
    public int deliveryRepay(String tid,String newTid){
        return deliveryMapper.deliveryRepay(tid,newTid);
    }

    public DeliveryDto getDelivery(String deliveryId){
        return deliveryMapper.getDelivery(deliveryId);
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
