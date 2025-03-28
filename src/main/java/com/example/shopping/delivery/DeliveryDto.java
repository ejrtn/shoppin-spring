package com.example.shopping.delivery;

import lombok.Data;

@Data
public class DeliveryDto {

    private String deliveryId;
    private String address;
    private int priceTotal;
    private String detail;
    private String userId;
    private String cdate;
    private String status;
}
