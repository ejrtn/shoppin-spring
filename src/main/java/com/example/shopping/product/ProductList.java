package com.example.shopping.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductList {
    private List<ProductDto> productDtos;
    private int productTotal;
    private int startNum;
    private int endNum;
}
