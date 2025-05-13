package com.example.shopping.delivery;

import lombok.Data;

@Data
public class DeliveryAddressListDto {
    private String userId;
    private String name;
    private String postcode;
    private String address;
    private String detailAddress;
    private String extraAddress;
    private String phon;
    private String defaultYn;
    private int deliveryAddressId;
}
