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

    public int tmpCartSave(List<CartDto> cartList){
        int r = 0;
        for(int i=0;i<cartList.size();i++){
            r = cartMapper.tmpCartSave(cartList.get(i));
        }
        return r;
    }

    public List<CartDto> cartList(String userId){
        return cartMapper.cartList(userId);
    }

    public int cartDelete(String userId, String productId){
        return cartMapper.cartDelete(userId,productId);
    }

    public int tmpCartDelete(String userId){
        return cartMapper.tmpCartDelete(userId);
    }

    public List<CartDto> tmpCartList(String userId) {
        return cartMapper.tmpCartList(userId);
    }
}
