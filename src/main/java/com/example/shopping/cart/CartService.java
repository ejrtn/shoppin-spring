package com.example.shopping.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartMapper cartMapper;

    public int cartSave(CartDto cartDto){
        return cartMapper.cartSave(cartDto);
    }

    public List<CartDto> cartList(String userId){
        return cartMapper.cartList(userId);
    }

    public int cartDelete(String userId, String productId){
        return cartMapper.cartDelete(userId,productId);
    }

}
