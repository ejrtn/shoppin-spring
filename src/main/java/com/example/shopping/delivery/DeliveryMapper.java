package com.example.shopping.delivery;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeliveryMapper {

    int deliverySave( DeliveryDto deliveryDto);

    List<DeliveryDto> deliveryList(DeliveryDto deliveryDto);

    int deliveryUpdate(DeliveryDto deliveryDto);

    DeliveryDto delivery(String deliveryId);
}
