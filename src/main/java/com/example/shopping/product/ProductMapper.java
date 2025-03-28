package com.example.shopping.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    boolean productSave(ProductDto productDto);

    List<ProductDto> productList(String saleYn);

    boolean productUpdate( ProductDto productDto);

    int productMinus(String productId, int cnt);

    int productPlus(String productId, int cnt);
}
