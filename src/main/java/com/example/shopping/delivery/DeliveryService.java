package com.example.shopping.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DeliveryService {

    @Autowired
    DeliveryMapper deliveryMapper;

    public boolean deliverySave(DeliveryDto deliveryDto){
        return deliveryMapper.deliverySave(deliveryDto);
    }

    public List<DeliveryDto> deliveryList(DeliveryDto deliveryDto){
        return deliveryMapper.deliveryList(deliveryDto);
    }

    public boolean deliveryUpdate(DeliveryDto deliveryDto){
        return deliveryMapper.deliveryUpdate(deliveryDto);
    }

    public DeliveryDto delivery(String deliveryId){
        return deliveryMapper.delivery(deliveryId);
    }

    public String deliveryNowLocation(){
        return "deliveryNowLocation";
    }

}
