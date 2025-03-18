package com.example.shopping.delivery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeliveryMapper {

    boolean deliverySave(@Param("delivery") DeliveryDto deliveryDto);

    List<Map<String,String>> deliveryList(@Param("delivery") DeliveryDto deliveryDto);

    boolean deliveryUpdate(@Param("delivery") DeliveryDto deliveryDto);

    String deliveryNowLocation();
}
