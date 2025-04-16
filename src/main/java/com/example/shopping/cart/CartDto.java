package com.example.shopping.cart;

import lombok.Data;

@Data
public class CartDto {

    private String productId;
    private String userId;
    private String cnt;
}
