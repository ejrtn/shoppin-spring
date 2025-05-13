package com.example.shopping.delivery;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DeliveryDto {

    private int deliveryId;
    private String name;
    private String address;
    private String phon;
    private String request;
    private int quantity;
    private int totalAmount;
    private LocalDateTime cdate;
    private String status;
    private String userId;
    private String tid;
    private List<DeliveryDetailDto> deliveryDetailDtos;
}
