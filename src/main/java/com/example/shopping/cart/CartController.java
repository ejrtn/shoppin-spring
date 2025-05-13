package com.example.shopping.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public int cartDelete(String userId, String productId){
        return cartService.cartDelete(userId,productId);
    }

    @GetMapping("cart")
    public String cart(){
        return "cart";
    }

    @GetMapping("buy")
    public String buy(@RequestParam String keyData){
        return "buy";
    }

    @PostMapping("tmpCartSave")
    @ResponseBody
    public String tmpCartSave(@RequestBody List<TmpCartDto> tmpCartDtos){
        return cartService.tmpCartSave(tmpCartDtos);
    }

    @PostMapping("tmpCartDelete")
    @ResponseBody
    public int tmpCartDelete(String keyData){
        return cartService.tmpCartDelete(keyData);
    }

    @PostMapping("tmpCartList")
    @ResponseBody
    public List<CartDto> tmpCartList(String keyData){
        return cartService.tmpCartList(keyData);
    }
}
