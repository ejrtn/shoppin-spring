package com.example.shopping.delivery;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DeliveryService {

    public boolean deliverySave(DeliveryDto deliveryDto){
        return true;
    }

    public List<Map<String,String>> deliveryList(DeliveryDto deliveryDto){
        return new ArrayList<>();
    }

    public boolean deliveryUpdate(DeliveryDto deliveryDto){
        return true;
    }

    public String deliveryNowLocation(){
        return "deliveryNowLocation";
    }


}
