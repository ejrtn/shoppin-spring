package com.example.shopping.product;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    public boolean productSave(ProductDto productDto){
        return true;
    }

    public List<Map<String,String>> productList(ProductDto productDto){
        return new ArrayList<>();
    }

    public boolean productUpdate(ProductDto productDto){
        return true;
    }

    public int productMinus(String productName, int cnt){
        return 1;
    }

    public int productPlus(String productName, int cnt){
        return 1;
    }
}
