package com.example.shopping.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    CartMapper cartMapper;

    public int cartSave(CartDto cartDto){
        return cartMapper.cartSave(cartDto);
    }

    public String tmpCartSave(List<TmpCartDto> tmpCartDtos){
        String key = String.valueOf(UUID.randomUUID());
        int r = 0;
        for(int i=0;i<tmpCartDtos.size();i++){
            tmpCartDtos.get(i).setKeyData(key);
            r = cartMapper.tmpCartSave(tmpCartDtos.get(i));
            if(r != 1) {
                key = null;
                break;
            }
        }
        return key;
    }

    public List<CartDto> cartList(String userId){
        return cartMapper.cartList(userId);
    }

    public int cartDelete(String userId, String productId){
        return cartMapper.cartDelete(userId,productId);
    }

    public int tmpCartDelete(String keyData){
        return cartMapper.tmpCartDelete(keyData);
    }

    public List<CartDto> tmpCartList(String keyData) {
        return cartMapper.tmpCartList(keyData);
    }
}
