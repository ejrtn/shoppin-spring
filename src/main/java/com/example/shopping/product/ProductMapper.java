package com.example.shopping.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    int productSave(ProductDto productDto);

    List<ProductDto> productList(String saleYn);

    int productUpdate( ProductDto productDto);

    List<ProductDto> top10();

    List<ProductDto> listAll(String category, String smallCategory, int startNum);

    ProductDto getProduct(String img);
}
