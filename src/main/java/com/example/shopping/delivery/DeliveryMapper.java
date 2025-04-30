package com.example.shopping.delivery;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryMapper {

    int deliverySave( DeliveryDto deliveryDto);

    List<DeliveryDto> deliveryList(DeliveryDto deliveryDto);

    int deliveryUpdate(DeliveryDto deliveryDto);

    DeliveryDto delivery(String deliveryId);

    int deliveryAddressSave(deliveryAddressListDto deliveryAddressListDto);

    List<deliveryAddressListDto> deliveryAddressList(String userId);

    deliveryAddressListDto deliveryAddressDefault(String userId);

    int deliveryAddressDelete(String deliveryAddressId);

    int deliveryAddressChange(deliveryAddressListDto deliveryAddressListDto);

    void deliveryAddressChangeDefaultYn(String userId);

    deliveryAddressListDto deliveryAddressOne(String deliveryAddressId);
}
