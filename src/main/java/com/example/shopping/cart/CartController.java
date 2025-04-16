package com.example.shopping.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("cartSave")
    @ResponseBody
    public int cartSave(CartDto cartDto){
        return cartService.cartSave(cartDto);
    }

    @PostMapping("cartList")
    @ResponseBody
    public List<CartDto> cartList(String userId){
        return cartService.cartList(userId);
    }

    @PostMapping("cartDelete")
    public int cartDelete(String userId, String productId){
        return cartService.cartDelete(userId,productId);
    }

    @GetMapping("cart")
    public String cart(){
        return "/cart";
    }
}
