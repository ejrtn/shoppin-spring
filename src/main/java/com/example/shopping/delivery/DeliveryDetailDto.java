package com.example.shopping.delivery;

import lombok.Data;

@Data
public class DeliveryDetailDto {
    private String productId;
    private String cnt;
    private String img;
    private int price;
    private int discount;
    private String productName;
    private int deliveryId;
}
