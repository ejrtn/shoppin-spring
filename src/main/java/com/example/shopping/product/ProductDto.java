package com.example.shopping.product;

import lombok.Data;

@Data
public class ProductDto {

    private String productName;
    private int price;
    private String category;
    private String explanation;
    private String productId;
    private String saleYn;
    private String img;
    private int discount;
    private int numberOfSalse;
    private String smallCategory;
    private String cnt;
}
