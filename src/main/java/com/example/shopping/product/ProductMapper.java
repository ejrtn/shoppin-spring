package com.example.shopping.product;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    boolean productSave(@Param("product") ProductDto productDto);

    List<Map<String,String>> productList(@Param("product") ProductDto productDto);

    boolean productUpdate(@Param("product") ProductDto productDto);

    int productMinus(@Param("productName") String productName, @Param("cnt") int cnt);

    int productPlus(@Param("productName") String productName, @Param("cnt") int cnt);
}
