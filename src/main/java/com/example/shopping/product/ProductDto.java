package com.example.shopping.product;

import lombok.Data;

@Data
public class ProductDto {

    private String productName;
    private int price;
    private int cnt;
    private String category;
    private String explanation;
    private String productId;
    private String saleYn;
}
