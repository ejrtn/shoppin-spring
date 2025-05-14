package com.example.shopping.delivery;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryMapper {

    int deliverySave( DeliveryDto deliveryDto);

    List<DeliveryDto> deliveryList(String userId,int start);

    int deliveryStatusUpdate(String tid,String status);

    DeliveryDto getDelivery(String deliveryId);

    int deliveryDetailSave(DeliveryDetailDto deliveryDetailDto);

    List<DeliveryDetailDto> deliveryDetailList(String deliveryId);

    int deliveryAddressSave(DeliveryAddressListDto deliveryAddressListDto);

    List<DeliveryAddressListDto> deliveryAddressList(String userId);

    DeliveryAddressListDto deliveryAddressDefault(String userId);

    int deliveryAddressDelete(String deliveryAddressId);

    int deliveryAddressChange(DeliveryAddressListDto deliveryAddressListDto);

    void deliveryAddressChangeDefaultYn(String userId);

    DeliveryAddressListDto deliveryAddressOne(String deliveryAddressId);

    int deliveryRepay(String tid,String newTid);
}
