package com.example.shopping.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    int productSave(ProductDto productDto);

    List<ProductDto> productList(int start);

    int productUpdate(ProductDto productDto);

    int productDelete(String productId);

    List<ProductDto> top10();

    List<ProductDto> listAll(String category, String smallCategory, int startNum);

    ProductDto getProduct(String img);

    int productTotal();
}
