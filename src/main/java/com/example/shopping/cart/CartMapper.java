package com.example.shopping.cart;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    int cartSave(CartDto cartDto);

    List<CartDto> cartList(String userId);

    int cartDelete(String userId, String productId);

    int tmpCartSave(TmpCartDto tmpCartDto);

    int tmpCartDelete(String keyData);

    List<CartDto> tmpCartList(String keyData);
}
